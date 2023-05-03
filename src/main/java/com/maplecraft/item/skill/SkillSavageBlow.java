package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillSavageBlow extends SkillItem {
    public static String itemName = "skill_savage_blow";
    public SkillSavageBlow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.BANDIT)
                        .setWeaponReq(EquipCategory.DAGGER)
                        .setSkillID(AllSkillKeyValues.SAVAGE_BLOW.skillID)
                        .setDamage(AllSkillKeyValues.SAVAGE_BLOW.damage)
                        .setAttackCount(AllSkillKeyValues.SAVAGE_BLOW.attackCount)
                        .setManaCost(AllSkillKeyValues.SAVAGE_BLOW.manaCost)
                        .setDelay(2)
                        .setAttackInterval(2),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(6)
                        .setTextureSize(47, 59));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.SAVAGE_BLOW.radius, AllSkillKeyValues.SAVAGE_BLOW.distance);
        scheduleDamage(player, target);
    }
}
