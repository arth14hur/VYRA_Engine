package com.vyra.demo;

import com.vyra.engine.graphics.VulkanContext;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.MemoryUtil;

public class Main {

    private long window;
    private VulkanContext vkContext;

    public void run() {
        initGLFW();
        vkContext = new VulkanContext();
        vkContext.init(); // initialise Vulkan

        loop(); // boucle principale

        cleanup(); // nettoyage
    }

    private void initGLFW() {
        // Callback pour afficher les erreurs GLFW dans la console
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Impossible d'initialiser GLFW");
        }

        // Vulkan uniquement
        GLFW.glfwWindowHint(GLFW.GLFW_CLIENT_API, GLFW.GLFW_NO_API);

        window = GLFW.glfwCreateWindow(800, 600, "Vyra Engine Demo", MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL) {
            throw new RuntimeException("Impossible de créer la fenêtre GLFW");
        }
    }

    private void loop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            GLFW.glfwPollEvents();
            // futur rendu Vulkan ici
        }
    }

    private void cleanup() {
        if (vkContext != null) {
            vkContext.cleanup(); // détruit l'instance Vulkan
        }
        if (window != MemoryUtil.NULL) {
            GLFW.glfwDestroyWindow(window);
        }
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
