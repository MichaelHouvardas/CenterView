package com.centerview;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CenterViewClient implements ClientModInitializer {
	private static KeyBinding centerViewKeyBinding;

	@Override
	public void onInitializeClient() {
		// Registers the key binding
		centerViewKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.centerview.center_view",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_C,
				"category.centerview"
		));

		// Registers the tick event handler
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (centerViewKeyBinding.wasPressed()) {
				centerView();
			}
		});
	}

	private void centerView() {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player != null) {
			client.player.setPitch(0.0F); // Centers the camera vertically
		}
	}
}
