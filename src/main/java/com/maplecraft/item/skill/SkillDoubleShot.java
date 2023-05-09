package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillDoubleShot extends SkillItem {
    public static String itemName = "skill_double_shot";
    public SkillDoubleShot() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ARCHER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(AllSkillKeyValues.DOUBLE_SHOT.skillId)
                        .setDamage(AllSkillKeyValues.DOUBLE_SHOT.damage)
                        .setAttackCount(AllSkillKeyValues.DOUBLE_SHOT.attackCount)
                        .setManaCost(AllSkillKeyValues.DOUBLE_SHOT.manaCost)
                        .setCooldown(AllSkillKeyValues.DOUBLE_SHOT.cooldown)
                        .setAttackInterval(4),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(6)
                        .setTextureSize(70, 72));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.DOUBLE_SHOT.radius, AllSkillKeyValues.DOUBLE_SHOT.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public String getSKillSound() {
        return "maplecraft:sound_attack_bow";
    }
}
