plugins {
    id("application")
    id("java")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.lwjgl.org/maven/")
    }
}

dependencies {
    implementation(project(":vyra-engine"))
}

application {
    mainClass.set("com.vyra.demo.Main")
}

// Désactiver toutes les tâches de test
tasks.matching { it.name.startsWith("test") }.configureEach {
    enabled = false
}
