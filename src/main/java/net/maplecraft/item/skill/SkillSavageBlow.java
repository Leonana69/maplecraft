package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillSavageBlow extends SkillItem {
    public static String itemName = "skill_savage_blow";
    public static int skillID = 4201005;
    public SkillSavageBlow() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.BANDIT)
                        .weaponReq(EquipCategory.DAGGER)
                        .damage(60)
                        .attackCount(6)
                        .delay(2)
                        .attackInterval(2)
                        .manaCost(4),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .textureSize(47, 59));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 2, 2);
            scheduleDamage(player, target);
        }
    }
}
