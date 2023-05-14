package me.tunisiaa.fermium.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import java.awt.*;
import java.awt.event.InputEvent;
import me.tunisiaa.fermium.client.FermiumClient;
import net.minecraft.client.option.KeyBinding;


public class SugarCaneMacro {
    static boolean orientation = false; //false = backward, true = sideways (forward)
    static InputUtil.Key backward = MinecraftClient.getInstance().options.backKey.getDefaultKey();
    static InputUtil.Key left = MinecraftClient.getInstance().options.leftKey.getDefaultKey();
    public static void macro(MinecraftClient client){


        try {
            System.setProperty("java.awt.headless", "false");
            final Robot robot = new Robot();
            if (FermiumClient.isMacroingSugarCane) {
                client.player.sendMessage(Text.literal("§BSugar cane macro §L§CSTOPPED!"), true);
                FermiumClient.isMacroingSugarCane = false;
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                KeyBinding.setKeyPressed((orientation) ? left :  backward, false);

            } else {
                // Simulate a mouse click
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                client.player.setPitch((float) (0.0 + Math.random() - 0.5));
                client.player.setYaw((float) (45.0 + Math.random() - 0.5));
                KeyBinding.setKeyPressed((orientation) ? left :  backward, true);



                /*
                while (MinecraftClient.getInstance().player.renderYaw < 44 || MinecraftClient.getInstance().player.renderYaw > 46) {
                    client.player.setYaw(MinecraftClient.getInstance().player.renderYaw += 1.0f);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                while (MinecraftClient.getInstance().player.renderPitch < -1 || MinecraftClient.getInstance().player.renderPitch > 1) {
                    client.player.setYaw(MinecraftClient.getInstance().player.renderYaw += 1.0f);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                */
            client.player.sendMessage(Text.literal("§BSugar cane macro §L§ASTARTED!"), true);
            FermiumClient.isMacroingSugarCane = true;
        }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public static void switchDirection(){
        KeyBinding.setKeyPressed((orientation) ? left :  backward, false);
        orientation = !orientation;
        KeyBinding.setKeyPressed((orientation) ? left :  backward, true);
    }
}
