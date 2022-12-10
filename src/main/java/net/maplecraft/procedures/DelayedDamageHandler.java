package net.maplecraft.procedures;

import net.maplecraft.client.renderer.SkillEffectRenderer;
import net.maplecraft.utils.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;

@Mod.EventBusSubscriber
public class DelayedDamageHandler {
    public static Queue<SkillDamageInstance> damageQueue = new PriorityQueue<>(new SkillDamageInstance.SkillDamageInstanceComparator());
    public static List<SkillEffectInstance> hitEffectList = new ArrayList<>();
    public static Queue<SkillProjectileInstance> projectileQueue = new PriorityQueue<>(new SkillProjectileInstance.SkillProjectileInstanceComparator());

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!player.level.isClientSide) {
            SkillDamageInstance instance = damageQueue.peek();
            while (instance != null && instance.tick <= player.tickCount) {
                instance = damageQueue.remove();
                if (AllSkillList.SKILLS.get(instance.skillID) != null) {
                    SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(instance.skillID).asItem();
                    skill.dealDamage(player, instance);
                }

                instance.attackCount -= 1;
                if (instance.attackCount > 0) {
                    instance.tick += instance.attackInterval;
                    damageQueue.add(instance);
                }

                instance = damageQueue.peek();
            }

            SkillProjectileInstance pInstance = projectileQueue.peek();
            while (pInstance != null && pInstance.tick <= player.tickCount) {
                pInstance = projectileQueue.remove();
                SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(pInstance.skillID).asItem();
                skill.generateProjectile(player, pInstance);
                pInstance = projectileQueue.peek();
            }
        }
    }

    @SubscribeEvent
    public static void scheduleHitEffectOnHit(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (event.getSource().getDirectEntity() instanceof MapleProjectileEntity entity) {
                if (AllSkillList.SKILLS.get(entity.skillID) != null) {
                    SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(entity.skillID).asItem();
                    skill.scheduleHitEffect(player, event.getEntity());
                }
            }
        }
    }

    @SubscribeEvent
    public static void renderSkillEffect(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES
            && Minecraft.getInstance().player != null) {
            Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
            Level world = Minecraft.getInstance().player.level;
            if (world.isClientSide) {
                float currentRenderTick = event.getRenderTick() + event.getPartialTick();
                for (int i = 0; i < hitEffectList.size(); i++) {
                    SkillEffectInstance instance = hitEffectList.get(i);
                    if (instance.tick < 0) {
                        instance.tick = currentRenderTick;
                    }

                    if (instance.currentAnime < 0 && instance.tick + instance.delay <= currentRenderTick) {
                        instance.tick = currentRenderTick;
                        instance.currentAnime = 0;
                    }

                    if (instance.currentAnime >= 0) {
                        SkillEffectRenderer.renderInWorld(event.getPartialTick(), event.getPoseStack(), camera, instance);
                        if (instance.tick + instance.tickPerFrame < currentRenderTick) {
                            instance.currentAnime += 1;
                            instance.tick += instance.tickPerFrame;
                        }

                        if (instance.currentAnime >= instance.animeCount) {
                            hitEffectList.remove(i);
                            i--;
                        }
                    }
                }
            }
        }
    }
}
