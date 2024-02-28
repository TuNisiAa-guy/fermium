package me.tunisiaa.fermium.modules;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;

public class Module {
    private KeyBinding keyBind;
    private boolean isToggled;

    private void initialize(){
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (keyBind.wasPressed()){
                SugarCaneMacro.macro(client);
            }
            while (keyBind.wasPressed()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
}
