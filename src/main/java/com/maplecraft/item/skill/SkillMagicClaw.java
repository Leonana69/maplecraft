package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillMagicClaw extends SkillItem {
    public static String itemName = "skill_magic_claw";
    public SkillMagicClaw() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.MAGICIAN)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(AllSkillKeyValues.MAGIC_CLAW.skillId)
                        .setDamage(AllSkillKeyValues.MAGIC_CLAW.damage)
                        .setAttackCount(AllSkillKeyValues.MAGIC_CLAW.attackCount)
                        .setManaCost(AllSkillKeyValues.MAGIC_CLAW.manaCost)
                        .setCooldown(AllSkillKeyValues.MAGIC_CLAW.cooldown)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(94, 95));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.MAGIC_CLAW.radius, AllSkillKeyValues.MAGIC_CLAW.distance);
        scheduleDamage(player, target);
    }
}
