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
                        .jobReq(JobCategory.WARRIOR)
                        .weaponReq(EquipCategory.SWORD)
                        .weaponReq(EquipCategory.SPEAR)
                        .skillID(POWER_STRIKE.skillID)
                        .damage(POWER_STRIKE.damage)
                        .attackCount(POWER_STRIKE.attackCount)
                        .manaCost(POWER_STRIKE.manaCost),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(2)
                        .textureSize(110, 118));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, POWER_STRIKE.radius, POWER_STRIKE.distance);
        scheduleDamage(player, target);
    }
}
