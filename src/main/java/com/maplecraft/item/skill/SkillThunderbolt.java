package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillThunderbolt extends SkillItem {
    public static String itemName = "skill_thunderbolt";
    public SkillThunderbolt() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WIZARD_IL)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(AllSkillKeyValues.THUNDERBOLT.skillId)
                        .setDamage(AllSkillKeyValues.THUNDERBOLT.damage)
                        .setAttackCount(AllSkillKeyValues.THUNDERBOLT.attackCount)
                        .setManaCost(AllSkillKeyValues.THUNDERBOLT.manaCost)
                        .setCooldown(AllSkillKeyValues.THUNDERBOLT.cooldown)
                        .setDelay(10)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(8)
                        .setTextureSize(68, 219));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, AllSkillKeyValues.THUNDERBOLT.radius, AllSkillKeyValues.THUNDERBOLT.distance, true);
        scheduleDamage(player, target);
    }
}
