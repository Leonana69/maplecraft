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
                        .jobReq(JobCategory.RANGER)
                        .weaponReq(EquipCategory.BOW)
                        .skillID(STRAFE.skillID)
                        .damage(STRAFE.damage)
                        .attackCount(STRAFE.attackCount)
                        .manaCost(STRAFE.manaCost)
                        .attackInterval(3),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(103, 97));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, STRAFE.radius, STRAFE.distance);
            scheduleProjectile(player, target);
        }
    }
}
