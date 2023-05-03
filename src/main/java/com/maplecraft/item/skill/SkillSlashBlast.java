package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillSlashBlast extends SkillItem {
    public static String itemName = "skill_slash_blast";
    public SkillSlashBlast() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WARRIOR)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(AllSkillKeyValues.SLASH_BLAST.skillID)
                        .setDamage(AllSkillKeyValues.SLASH_BLAST.damage)
                        .setAttackCount(AllSkillKeyValues.SLASH_BLAST.attackCount)
                        .setManaCost(AllSkillKeyValues.SLASH_BLAST.manaCost)
                        .setHealthCost(1),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(122, 99));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, AllSkillKeyValues.SLASH_BLAST.radius, AllSkillKeyValues.SLASH_BLAST.distance);
        scheduleDamage(player, target);
    }
}
