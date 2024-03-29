package com.maplecraft.procedures;

import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.AllSkillList;
import com.maplecraft.utils.SkillDamageInstance;
import com.maplecraft.utils.SkillProjectileInstance;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber
public class SkillDamageHandler {
    public static Queue<SkillDamageInstance> damageQueue = new PriorityQueue<>(new SkillDamageInstance.SkillDamageInstanceComparator());
    public static Queue<SkillProjectileInstance> projectileQueue = new PriorityQueue<>(new SkillProjectileInstance.SkillProjectileInstanceComparator());

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!player.level.isClientSide) {
            SkillDamageInstance instance = damageQueue.peek();
            while (instance != null && instance.tick <= player.tickCount) {
                instance = damageQueue.remove();
                if (AllSkillList.SKILLS.get(instance.skillId) != null) {
                    SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(instance.skillId).asItem();
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
                SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(pInstance.skillId).asItem();
                skill.generateProjectile(player, pInstance);
                pInstance = projectileQueue.peek();
            }
        }
    }

    @SubscribeEvent
    public static void scheduleHitEffectOnHit(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (event.getSource().getDirectEntity() instanceof MapleProjectileEntity entity) {
                if (AllSkillList.SKILLS.get(entity.skillId) != null) {
                    SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(entity.skillId).asItem();
                    skill.scheduleHitEffect(player, event.getEntity());
                }
            }
        }
    }
}
