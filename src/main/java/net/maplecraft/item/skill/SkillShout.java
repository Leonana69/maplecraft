package net.maplecraft.item.skill;

import com.mojang.math.Vector3f;
import net.maplecraft.utils.*;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.SHOUT;

public class SkillShout extends SkillItem {
    public static String itemName = "skill_shout";
    public SkillShout() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.FIGHTER)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setSkillID(SHOUT.skillID)
                        .setDamage(SHOUT.damage)
                        .setAttackCount(SHOUT.attackCount)
                        .setManaCost(SHOUT.manaCost)
                        .setDelay(14),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(70, 68));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, SHOUT.radius, SHOUT.distance, true);
        scheduleDamage(player, target);
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(new DustParticleOptions(
                            new Vector3f(0.2F, 0.95F, 0.95F), 0.8F),
                    entity.getX(), entity.getY(), entity.getZ(),
                    60,
                    0.2, 1.0, 0.2,
                    1.5);
        }
    }
}
