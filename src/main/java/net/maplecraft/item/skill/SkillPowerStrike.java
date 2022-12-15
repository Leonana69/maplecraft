package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.POWER_STRIKE;

public class SkillPowerStrike extends SkillItem {
    public static String itemName = "skill_power_strike";
    public SkillPowerStrike() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WARRIOR)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(POWER_STRIKE.skillID)
                        .setDamage(POWER_STRIKE.damage)
                        .setAttackCount(POWER_STRIKE.attackCount)
                        .setManaCost(POWER_STRIKE.manaCost),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(110, 118));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, POWER_STRIKE.radius, POWER_STRIKE.distance);
        scheduleDamage(player, target);
    }
}
