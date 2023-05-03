package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillLuckySeven extends SkillItem {
    public static String itemName = "skill_lucky_seven";
    public SkillLuckySeven() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ROGUE)
                        .setWeaponReq(EquipCategory.CLAW)
                        .setSkillID(AllSkillKeyValues.LUCKY_SEVEN.skillID)
                        .setDamage(AllSkillKeyValues.LUCKY_SEVEN.damage)
                        .setAttackCount(AllSkillKeyValues.LUCKY_SEVEN.attackCount)
                        .setManaCost(AllSkillKeyValues.LUCKY_SEVEN.manaCost)
                        .setAttackInterval(4),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(50, 51));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.LUCKY_SEVEN.radius, AllSkillKeyValues.LUCKY_SEVEN.distance);
        scheduleProjectile(player, target);
    }
}
