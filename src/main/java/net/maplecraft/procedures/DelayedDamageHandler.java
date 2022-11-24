package net.maplecraft.procedures;

import net.maplecraft.client.renderer.SkillEffectRenderer;
import net.maplecraft.utils.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

@Mod.EventBusSubscriber
public class DelayedDamageHandler {
    public static Queue<SkillDamageInstance> damageQueue = new PriorityQueue<>(new SkillDamageInstance.SkillDamageInstanceComparator());
    public static List<SkillHitEffectInstance> hitEffectQueue = new ArrayList<>();

    public static Queue<SkillProjectileInstance> projectileQueue = new PriorityQueue<>(new SkillProjectileInstance.SkillProjectileInstanceComparator());

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        SkillDamageInstance instance = damageQueue.peek();
        Player player = event.player;
        while (instance != null && instance.tick <= player.level.getGameTime()) {
            instance = damageQueue.remove();
            SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(instance.skillID).asItem();
            skill.dealDamage(player, instance);
            instance.attackCount -= 1;
            if (instance.attackCount > 0) {
                instance.tick += instance.attackInterval;
                damageQueue.add(instance);
            }

            instance = damageQueue.peek();
        }

        SkillProjectileInstance pInstance = projectileQueue.peek();
        while (pInstance != null && pInstance.tick <= player.level.getGameTime()) {
            pInstance = projectileQueue.remove();

            SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(pInstance.skillID).asItem();
            skill.generateProjectile(player, pInstance);
            pInstance = projectileQueue.peek();
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
