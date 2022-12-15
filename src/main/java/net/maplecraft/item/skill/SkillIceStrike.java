package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ICE_STRIKE;

public class SkillIceStrike extends SkillItem {
    public static String itemName = "skill_ice_strike";
    public SkillIceStrike() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.MAGE_IL)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(ICE_STRIKE.skillID)
                        .setDamage(ICE_STRIKE.damage)
                        .setAttackCount(ICE_STRIKE.attackCount)
                        .setManaCost(ICE_STRIKE.manaCost)
                        .setDelay(10)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(6)
                        .setTextureSize(87, 247));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, ICE_STRIKE.radius, ICE_STRIKE.distance, true);
        scheduleDamage(player, target);
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.SNOWFLAKE,
                    player.getX(), player.getY(), player.getZ(),
                    50,
                    2, 2, 2,
                    0.1);
            level.sendParticles(ParticleTypes.FIREWORK,
                    player.getX(), player.getY(), player.getZ(),
                    50,
                    2, 2, 2,
                    0.1);
        }
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 4));
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.SNOWFLAKE,
                    player.getX(), player.getY(), player.getZ(),
                    10,
                    0.2, 0.2, 0.2,
                    0.2);
        }
    }
}
