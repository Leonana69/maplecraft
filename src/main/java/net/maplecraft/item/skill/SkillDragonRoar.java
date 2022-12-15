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

import static net.maplecraft.utils.AllSkillKeyValues.DRAGON_ROAR;

public class SkillDragonRoar extends SkillItem {
    public static String itemName = "skill_dragon_roar";
    public SkillDragonRoar() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.DRAGON_KNIGHT)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(DRAGON_ROAR.skillID)
                        .setDamage(DRAGON_ROAR.damage)
                        .setAttackCount(DRAGON_ROAR.attackCount)
                        .setManaCost(DRAGON_ROAR.manaCost)
                        .setHealthCost(4)
                        .setDelay(14),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(104, 105));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, DRAGON_ROAR.radius, DRAGON_ROAR.distance, true);
        scheduleDamage(player, target);
        if (player.level instanceof ServerLevel level) {
            double radius = DRAGON_ROAR.radius / 2.0;
            level.sendParticles(ParticleTypes.DRAGON_BREATH,
                    player.getX(), player.getY(), player.getZ(),
                    400,
                    radius, radius, radius,
                    0.1);
        }
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.DRAGON_BREATH,
                    entity.getX(), entity.getY() + entity.getBbHeight() / 2, entity.getZ(),
                    50,
                    0.2, 0.2, 0.2,
                    0.2);
        }
    }
}
