package com.vyra.engine.graphics;

public class Renderer {
    private VulkanContext context;

    public Renderer(VulkanContext context) {
        this.context = context;
    }

    public void render() {
        // Pour l’instant on ne fait que clear la frame
        // Ensuite on ajoutera swapchain et draw commands
    }
}
