plugins {
    id("java")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.lwjgl.org/maven/")
    }
}

val lwjglVersion = "3.3.6"
val lwjglNatives = "natives-linux" // <-- ici on déclare la variable

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    val lwjglModules = listOf("lwjgl", "lwjgl-glfw", "lwjgl-vulkan", "lwjgl-stb")
    lwjglModules.forEach { module ->
        // nécessaire pour la compilation
        implementation("org.lwjgl:$module:$lwjglVersion")
        // nécessaire pour exécution natives Linux
        runtimeOnly("org.lwjgl:$module:$lwjglVersion:$lwjglNatives")
    }

    implementation("org.joml:joml:1.10.5")
}

// Désactiver toutes les tâches de test
tasks.matching { it.name.startsWith("test") }.configureEach {
    enabled = false
}
