package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillPowerStrike extends SkillItem {
    public static String itemName = "skill_power_strike";
    public static int skillID = 1001004;
    public SkillPowerStrike() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.WARRIOR)
                        .weaponReq(EquipCategory.SWORD)
                        .weaponReq(EquipCategory.SPEAR)
                        .damage(180)
                        .manaCost(2),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(2)
                        .textureSize(110, 118));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 3, 2);
            if (!target.isEmpty()) {
                scheduleDamage(player, target);
            }
        }
    }
}
