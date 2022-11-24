package net.maplecraft.procedures;

import net.maplecraft.client.renderer.SkillEffectRenderer;
import net.maplecraft.utils.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Mod.EventBusSubscriber
public class DelayedDamageHandler {
    public static Queue<SkillDamageInstance> damageQueue = new PriorityQueue<>(new SkillDamageInstance.SkillDamageInstanceComparator());
    public static List<SkillHitEffectInstance> hitEffectQueue = new ArrayList<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        SkillDamageInstance instance = damageQueue.peek();
        while (instance != null && instance.tick <= event.player.level.getGameTime()) {
            instance = damageQueue.remove();
            SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(instance.skillID).asItem();
            skill.dealDamage(event.player, instance);
            instance.attackCount -= 1;
            if (instance.attackCount > 0) {
                instance.tick += instance.delay;
                damageQueue.add(instance);
            }

            instance = damageQueue.peek();
        }
    }

    @SubscribeEvent
    public static void renderSkillEffect(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES
            && Minecraft.getInstance().player != null) {
            Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
            Level world = Minecraft.getInstance().player.level;

            if (hitEffectQueue.size() > 0) {
                System.out.println("Effect size: " + hitEffectQueue.size());
            }

            for (int i = 0; i < hitEffectQueue.size(); i++) {
                SkillHitEffectInstance instance = hitEffectQueue.get(i);

                System.out.println("tick: " + world.getGameTime());
                System.out.println("cur: " + instance.currentAnime);

                if (instance.currentAnime < 0 && instance.tick + instance.delay <= world.getGameTime()) {
                    instance.tick = world.getGameTime();
                    instance.currentAnime = 0;
                }

                if (instance.currentAnime >= 0) {
                    SkillEffectRenderer.renderInWorld(event.getPartialTick(), event.getPoseStack(), camera, instance);
                    if (instance.tick + instance.tickPerFrame < world.getGameTime()) {
                        instance.currentAnime += 1;
                        instance.tick += instance.tickPerFrame;
                    }

                    if (instance.currentAnime >= instance.animeCount) {
                        hitEffectQueue.remove(i);
                        i--;
                    }
                }
            }
        }
    }
}
