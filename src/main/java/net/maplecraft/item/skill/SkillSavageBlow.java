package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.SAVAGE_BLOW;

public class SkillSavageBlow extends SkillItem {
    public static String itemName = "skill_savage_blow";
    public SkillSavageBlow() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.BANDIT)
                        .weaponReq(EquipCategory.DAGGER)
                        .skillID(SAVAGE_BLOW.skillID)
                        .damage(SAVAGE_BLOW.damage)
                        .attackCount(SAVAGE_BLOW.attackCount)
                        .manaCost(SAVAGE_BLOW.manaCost)
                        .delay(2)
                        .attackInterval(2),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .textureSize(47, 59));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, SAVAGE_BLOW.radius, SAVAGE_BLOW.distance);
            scheduleDamage(player, target);
        }
    }
}
