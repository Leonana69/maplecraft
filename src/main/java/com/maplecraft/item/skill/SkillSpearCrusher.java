package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillSpearCrusher extends SkillItem {
    public static String itemName = "skill_spear_crusher";
    public SkillSpearCrusher() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.DRAGON_KNIGHT)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(AllSkillKeyValues.SPEAR_CRUSHER.skillId)
                        .setDamage(AllSkillKeyValues.SPEAR_CRUSHER.damage)
                        .setAttackCount(AllSkillKeyValues.SPEAR_CRUSHER.attackCount)
                        .setManaCost(AllSkillKeyValues.SPEAR_CRUSHER.manaCost)
                        .setCooldown(AllSkillKeyValues.SPEAR_CRUSHER.cooldown)
                        .setDelay(12)
                        .setAttackInterval(3),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(120, 107));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.SPEAR_CRUSHER.radius, AllSkillKeyValues.SPEAR_CRUSHER.distance, true);
        scheduleDamage(player, target);
    }
}
