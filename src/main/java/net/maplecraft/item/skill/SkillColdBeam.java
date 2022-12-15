package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.COLD_BEAM;

public class SkillColdBeam extends SkillItem {
    public static String itemName = "skill_cold_beam";
    public SkillColdBeam() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WIZARD_IL)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(COLD_BEAM.skillID)
                        .setDamage(COLD_BEAM.damage)
                        .setAttackCount(COLD_BEAM.attackCount)
                        .setManaCost(COLD_BEAM.manaCost)
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
        List<LivingEntity> target = getClosestEntity(player, COLD_BEAM.radius, COLD_BEAM.distance);
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
