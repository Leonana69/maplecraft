package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillDrain extends SkillItem {
    public static String itemName = "skill_drain";
    public SkillDrain() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ASSASSIN)
                        .setWeaponReq(EquipCategory.CLAW)
                        .setSkillID(AllSkillKeyValues.DRAIN.skillID)
                        .setDamage(AllSkillKeyValues.DRAIN.damage)
                        .setAttackCount(AllSkillKeyValues.DRAIN.attackCount)
                        .setManaCost(AllSkillKeyValues.DRAIN.manaCost),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(6)
                        .setTextureSize(68, 66));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.DRAIN.radius, AllSkillKeyValues.DRAIN.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        player.setHealth(player.getHealth() + 2);
    }
}
