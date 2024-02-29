package me.tunisiaa.fermium.modules.combat;

import me.tunisiaa.fermium.modules.Module;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


public class KillAura extends Module {
    private byte preciseness = 20;
    private int xp = 1;

    public void start(){
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(client.world != null){
                client.cameraEntity.noClip = false;
                client.player.noClip = false;
                client.inGameHud.vignetteDarkness = 1000f;
                Entity closest;
                ArrayList e = (ArrayList) client.world.getEntitiesByType(EntityType.PIG,new Box(client.player.getX()-10,client.player.getY()-10,client.player.getZ()-10,client.player.getX()+10,client.player.getY()+10,client.player.getZ()+10), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR);
                if(!e.isEmpty()){
                    closest = (Entity) e.get(0);
                    for(Object entity : e){
                        if(((Entity) entity).distanceTo(client.player) < closest.distanceTo(client.player)){
                            closest = (Entity) entity;
                        }
                    }
                    client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, closest.getPos());
                    client.player.swingHand(client.player.getActiveHand());
                    client.player.spawnSweepAttackParticles();

                }

                client.player.setVelocity(0d, 1000000f, 0d);
            }
        });
    }
}
