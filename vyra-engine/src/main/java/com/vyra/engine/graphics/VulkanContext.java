package com.vyra.engine.graphics;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.vulkan.VkApplicationInfo;
import org.lwjgl.vulkan.VkInstance;
import org.lwjgl.vulkan.VkInstanceCreateInfo;

import static org.lwjgl.vulkan.VK10.*;
import static org.lwjgl.vulkan.VK13.VK_API_VERSION_1_3;

public class VulkanContext {

    private VkInstance instance; // maintenant objet wrapper

    public void init() {
        try (MemoryStack stack = MemoryStack.stackPush()) {

            // 1️⃣ Application info
            VkApplicationInfo appInfo = VkApplicationInfo.callocStack(stack)
                    .sType(VK_STRUCTURE_TYPE_APPLICATION_INFO)
                    .pApplicationName(stack.UTF8("Vyra Engine"))
                    .applicationVersion(VK_MAKE_VERSION(1, 0, 0))
                    .pEngineName(stack.UTF8("Vyra Engine"))
                    .engineVersion(VK_MAKE_VERSION(1, 0, 0))
                    .apiVersion(VK_API_VERSION_1_3);

            // 2️⃣ Instance create info
            VkInstanceCreateInfo createInfo = VkInstanceCreateInfo.callocStack(stack)
                    .sType(VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO)
                    .pApplicationInfo(appInfo);

            // 3️⃣ Créer PointerBuffer pour récupérer handle
            PointerBuffer pInstance = stack.mallocPointer(1);

            if (vkCreateInstance(createInfo, null, pInstance) != VK_SUCCESS) {
                throw new RuntimeException("Impossible de créer l'instance Vulkan");
            }

            // 4️⃣ Encapsuler le handle dans VkInstance wrapper
            instance = new VkInstance(pInstance.get(0), createInfo);

            System.out.println("Vulkan instance created: " + instance.address());
        }
    }

    public VkInstance getInstance() {
        return instance;
    }

    public void cleanup() {
        if (instance != null) {
            vkDestroyInstance(instance, null); // compatible avec wrapper
            System.out.println("Vulkan instance destroyed");
        }
    }
}
