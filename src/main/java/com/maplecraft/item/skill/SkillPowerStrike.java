package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillPowerStrike extends SkillItem {
    public static String itemName = "skill_power_strike";
    public SkillPowerStrike() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WARRIOR)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(AllSkillKeyValues.POWER_STRIKE.skillID)
                        .setDamage(AllSkillKeyValues.POWER_STRIKE.damage)
                        .setAttackCount(AllSkillKeyValues.POWER_STRIKE.attackCount)
                        .setManaCost(AllSkillKeyValues.POWER_STRIKE.manaCost),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(110, 118));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.POWER_STRIKE.radius, AllSkillKeyValues.POWER_STRIKE.distance);
        scheduleDamage(player, target);
    }
}
