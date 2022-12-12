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
                        .jobReq(JobCategory.MAGE_IL)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(ICE_STRIKE.skillID)
                        .damage(ICE_STRIKE.damage)
                        .attackCount(ICE_STRIKE.attackCount)
                        .manaCost(ICE_STRIKE.manaCost)
                        .delay(10)
                        .isMagic(true),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .textureSize(87, 247));
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
