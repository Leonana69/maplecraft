package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.STRAFE;

public class SkillStrafe extends SkillItem {
    public static String itemName = "skill_strafe";
    public SkillStrafe() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.ARCHER)
                        .weaponReq(EquipCategory.BOW)
                        .weaponReq(EquipCategory.CROSSBOW)
                        .skillID(STRAFE.skillID)
                        .damage(STRAFE.damage)
                        .attackCount(STRAFE.attackCount)
                        .manaCost(STRAFE.manaCost)
                        .attackInterval(3),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(103, 97));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, STRAFE.radius, STRAFE.distance);
        scheduleProjectile(player, target);
    }
}
