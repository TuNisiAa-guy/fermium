package me.tunisiaa.fermium.client;

import me.tunisiaa.fermium.modules.SugarCaneMacro;
import me.tunisiaa.fermium.modules.combat.KillAura;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class FermiumClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    Vec3d lastPos = new Vec3d(0, 0, 0);
    public static boolean isMacroingSugarCane = false;
    private static KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Sugar cane macro",InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT,"Fermium"));
    @Override
    public void onInitializeClient() {

        System.setProperty("java.awt.headless", "false");
        System.out.println("Fermium utility mod loaded successfully!");
        ClientTickEvents.START_CLIENT_TICK.register(client -> {

            if (keyBinding.wasPressed()){
                SugarCaneMacro.macro(client);
            }
            while (keyBinding.wasPressed()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (client.player instanceof ClientPlayerEntity && isMacroingSugarCane) {
                if (client.player.getPos() == lastPos){
                    SugarCaneMacro.switchDirection();
                }
                lastPos = client.player.getPos();
            }

        });
        KillAura ka = new KillAura();
        ka.start();
    }

}
