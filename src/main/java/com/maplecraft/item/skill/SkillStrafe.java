package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillStrafe extends SkillItem {
    public static String itemName = "skill_strafe";
    public SkillStrafe() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ARCHER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(AllSkillKeyValues.STRAFE.skillID)
                        .setDamage(AllSkillKeyValues.STRAFE.damage)
                        .setAttackCount(AllSkillKeyValues.STRAFE.attackCount)
                        .setManaCost(AllSkillKeyValues.STRAFE.manaCost)
                        .setAttackInterval(3),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(103, 97));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.STRAFE.radius, AllSkillKeyValues.STRAFE.distance);
        scheduleProjectile(player, target);
    }
}
