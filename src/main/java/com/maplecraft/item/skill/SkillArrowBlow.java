package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillArrowBlow extends SkillItem {
    public static String itemName = "skill_arrow_blow";
    public SkillArrowBlow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ARCHER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(AllSkillKeyValues.ARROW_BLOW.skillId)
                        .setDamage(AllSkillKeyValues.ARROW_BLOW.damage)
                        .setAttackCount(AllSkillKeyValues.ARROW_BLOW.attackCount)
                        .setManaCost(AllSkillKeyValues.ARROW_BLOW.manaCost)
                        .setCooldown(AllSkillKeyValues.ARROW_BLOW.cooldown),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(113, 134));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.ARROW_BLOW.radius, AllSkillKeyValues.ARROW_BLOW.distance);
        scheduleProjectile(player, target);
    }
}
