package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.SHINING_RAY;

public class SkillShiningRay extends SkillItem {
    public static String itemName = "skill_shining_ray";
    public SkillShiningRay() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.PRIEST)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(SHINING_RAY.skillID)
                        .setDamage(SHINING_RAY.damage)
                        .setAttackCount(SHINING_RAY.attackCount)
                        .setManaCost(SHINING_RAY.manaCost)
                        .setDelay(10)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(137, 143));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, SHINING_RAY.radius, SHINING_RAY.distance, true);
        scheduleDamage(player, getUndeadEntity(target), 1.2F);
        scheduleDamage(player, getLivingEntity(target));
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.BLUE_ICE.defaultBlockState()),
                    entity.getX(), entity.getY() + entity.getBbHeight() / 2, entity.getZ(),
                    20,
                    0.3, 0.4, 0.3,
                    0.2);
        }
    }
}
