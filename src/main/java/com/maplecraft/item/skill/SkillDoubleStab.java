package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillDoubleStab extends SkillItem {
    public static String itemName = "skill_double_stab";
    public SkillDoubleStab() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ROGUE)
                        .setWeaponReq(EquipCategory.DAGGER)
                        .setSkillID(AllSkillKeyValues.DOUBLE_STAB.skillID)
                        .setDamage(AllSkillKeyValues.DOUBLE_STAB.damage)
                        .setAttackCount(AllSkillKeyValues.DOUBLE_STAB.attackCount)
                        .setManaCost(AllSkillKeyValues.DOUBLE_STAB.manaCost)
                        .setDelay(5)
                        .setAttackInterval(3),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(121, 117));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.DOUBLE_STAB.radius, AllSkillKeyValues.DOUBLE_STAB.distance);
        scheduleDamage(player, target);
    }
}
