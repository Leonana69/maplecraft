package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillColdBeam extends SkillItem {
    public static String itemName = "skill_cold_beam";
    public SkillColdBeam() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WIZARD_IL)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(AllSkillKeyValues.COLD_BEAM.skillId)
                        .setDamage(AllSkillKeyValues.COLD_BEAM.damage)
                        .setAttackCount(AllSkillKeyValues.COLD_BEAM.attackCount)
                        .setManaCost(AllSkillKeyValues.COLD_BEAM.manaCost)
                        .setCooldown(AllSkillKeyValues.COLD_BEAM.cooldown)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(6)
                        .setDelay(2)
                        .setHitEffectOnHit(false)
                        .setTextureSize(79, 179));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.COLD_BEAM.radius, AllSkillKeyValues.COLD_BEAM.distance);
        scheduleDamage(player, target);
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4));
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.FIREWORK,
                    entity.getX(), entity.getY() + entity.getBbHeight() / 2, entity.getZ(),
                    10,
                    0.2, 0.4, 0.2,
                    0.1);
        }
    }
}
