package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.mojang.math.Vector3f;
import com.maplecraft.utils.*;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillHeal extends SkillItem {
    public static String itemName = "skill_heal";
    public SkillHeal() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CLERIC)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(AllSkillKeyValues.HEAL.skillId)
                        .setDamage(AllSkillKeyValues.HEAL.damage)
                        .setAttackCount(AllSkillKeyValues.HEAL.attackCount)
                        .setManaCost(AllSkillKeyValues.HEAL.manaCost)
                        .setCooldown(AllSkillKeyValues.HEAL.cooldown)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(7)
                        .setTextureSize(48, 51));
    }

    @Override
    public void skillEffect(Player player) {
        player.setHealth(player.getHealth() + 4);
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, AllSkillKeyValues.HEAL.radius, AllSkillKeyValues.HEAL.distance, true);
        scheduleDamage(player, getUndeadEntity(target));
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(new DustParticleOptions(
                            new Vector3f(0.1F, 0.95F, 0.1F), 1.0F),
                    entity.getX(), entity.getY() + entity.getBbHeight() / 2, entity.getZ(),
                    20,
                    0.3, 0.4, 0.3,
                    0.2);
        }
    }
}
