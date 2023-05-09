package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.mojang.math.Vector3f;
import com.maplecraft.utils.*;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillShout extends SkillItem {
    public static String itemName = "skill_shout";
    public SkillShout() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.FIGHTER)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setSkillID(AllSkillKeyValues.SHOUT.skillId)
                        .setDamage(AllSkillKeyValues.SHOUT.damage)
                        .setAttackCount(AllSkillKeyValues.SHOUT.attackCount)
                        .setManaCost(AllSkillKeyValues.SHOUT.manaCost)
                        .setCooldown(AllSkillKeyValues.SHOUT.cooldown)
                        .setDelay(14),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(70, 68));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, AllSkillKeyValues.SHOUT.radius, AllSkillKeyValues.SHOUT.distance, true);
        scheduleDamage(player, target);
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(new DustParticleOptions(
                            new Vector3f(0.2F, 0.95F, 0.95F), 0.8F),
                    entity.getX(), entity.getY() + entity.getBbHeight() / 2, entity.getZ(),
                    60,
                    0.2, 1.0, 0.2,
                    1.5);
        }
    }
}
