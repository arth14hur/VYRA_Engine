package com.vyra.engine.core;

import com.vyra.engine.graphics.Renderer;
import com.vyra.engine.graphics.VulkanContext;

public class Engine {
    private Window window;
    private VulkanContext vulkanContext;
    private Renderer renderer;

    public Engine() {
        window = new Window(800, 600, "Vyra Engine");
        vulkanContext = new VulkanContext();
        renderer = new Renderer(vulkanContext);
    }

    public void start() {
        window.init();
        vulkanContext.init();

        while (!window.shouldClose()) {
            renderer.render();
            window.pollEvents();
        }

        vulkanContext.cleanup();
        window.destroy();
    }
}
