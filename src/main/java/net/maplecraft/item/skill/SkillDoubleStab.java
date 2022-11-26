package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillDoubleStab extends SkillItem {
    public static String itemName = "skill_double_stab";
    public static int skillID = 4001334;
    public SkillDoubleStab() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.THIEF)
                        .weaponReq(EquipCategory.DAGGER)
                        .damage(100)
                        .attackCount(2)
                        .delay(5)
                        .attackInterval(3)
                        .manaCost(3),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(121, 117));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 2, 2);
            scheduleDamage(player, target);
        }
    }
}
