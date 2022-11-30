package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.DOUBLE_STAB;

public class SkillDoubleStab extends SkillItem {
    public static String itemName = "skill_double_stab";
    public SkillDoubleStab() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.THIEF)
                        .weaponReq(EquipCategory.DAGGER)
                        .skillID(DOUBLE_STAB.skillID)
                        .damage(DOUBLE_STAB.damage)
                        .attackCount(DOUBLE_STAB.attackCount)
                        .manaCost(DOUBLE_STAB.manaCost)
                        .delay(5)
                        .attackInterval(3),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(121, 117));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, DOUBLE_STAB.radius, DOUBLE_STAB.distance);
        scheduleDamage(player, target);
    }
}
