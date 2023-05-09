package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public class SkillIronArrow extends SkillItem {
    public static String itemName = "skill_iron_arrow";
    public SkillIronArrow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CROSSBOWMAN)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(AllSkillKeyValues.IRON_ARROW.skillId)
                        .setDamage(AllSkillKeyValues.IRON_ARROW.damage)
                        .setAttackCount(AllSkillKeyValues.IRON_ARROW.attackCount)
                        .setManaCost(AllSkillKeyValues.IRON_ARROW.manaCost)
                        .setCooldown(AllSkillKeyValues.IRON_ARROW.cooldown),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(63, 106));
        this.consumeProjectile = true;
        this.projectilePierceLevel = 6;
    }

    @Override
    public void skillEffect(Player player) {
        scheduleProjectile(player, new ArrayList<>());
    }
}
