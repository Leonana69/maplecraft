package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.EXPLOSION;

public class SkillExplosion extends SkillItem {
    public static String itemName = "skill_explosion";
    public SkillExplosion() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.MAGE_FP)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(EXPLOSION.skillID)
                        .setDamage(EXPLOSION.damage)
                        .setAttackCount(EXPLOSION.attackCount)
                        .setManaCost(EXPLOSION.manaCost)
                        .setDelay(10)
                        .setIsMagic(true),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, EXPLOSION.radius, EXPLOSION.distance, true);
        scheduleDamage(player, target);
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.LARGE_SMOKE,
                    player.getX(), player.getY(), player.getZ(),
                    50,
                    2, 2, 2,
                    0.1);
            level.sendParticles(ParticleTypes.FLAME,
                    player.getX(), player.getY(), player.getZ(),
                    50,
                    2, 2, 2,
                    0.1);
            level.sendParticles(ParticleTypes.LAVA,
                    player.getX(), player.getY(), player.getZ(),
                    50,
                    2, 2, 2,
                    0.1);
            level.sendParticles(ParticleTypes.EXPLOSION,
                    player.getX(), player.getY(), player.getZ(),
                    15,
                    2, 2, 2,
                    0);
        }
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.FLAME,
                    entity.getX(), entity.getY(), entity.getZ(),
                    10,
                    0.2, 0.2, 0.2,
                    0.2);
            level.sendParticles(ParticleTypes.EXPLOSION,
                    entity.getX(), entity.getY(), entity.getZ(),
                    3,
                    0.2, 0.2, 0.2,
                    0.2);
        }

        entity.setSecondsOnFire(2);
    }
}
