package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillDragonFury extends SkillItem {
    public static String itemName = "skill_dragon_fury";
    public SkillDragonFury() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WARRIOR)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(AllSkillKeyValues.DRAGON_FURY.skillId)
                        .setDamage(AllSkillKeyValues.DRAGON_FURY.damage)
                        .setAttackCount(AllSkillKeyValues.DRAGON_FURY.attackCount)
                        .setManaCost(AllSkillKeyValues.DRAGON_FURY.manaCost)
                        .setCooldown(AllSkillKeyValues.DRAGON_FURY.cooldown),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(8)
                        .setTextureSize(163, 273));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, AllSkillKeyValues.DRAGON_FURY.radius, AllSkillKeyValues.DRAGON_FURY.distance);
        scheduleDamage(player, target);
    }
}
