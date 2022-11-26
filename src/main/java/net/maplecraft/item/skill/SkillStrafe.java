package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillStrafe extends SkillItem {
    public static String itemName = "skill_strafe";
    public static int skillID = 3111006;
    public SkillStrafe() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.RANGER)
                        .weaponReq(EquipCategory.BOW)
                        .damage(100)
                        .attackCount(4)
                        .attackInterval(3)
                        .manaCost(6),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(103, 97));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 4, 15);
            scheduleProjectile(player, target);
        }
    }
}
