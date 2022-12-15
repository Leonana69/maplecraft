package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
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
                        .setJobReq(JobCategory.ROGUE)
                        .setWeaponReq(EquipCategory.DAGGER)
                        .setSkillID(DOUBLE_STAB.skillID)
                        .setDamage(DOUBLE_STAB.damage)
                        .setAttackCount(DOUBLE_STAB.attackCount)
                        .setManaCost(DOUBLE_STAB.manaCost)
                        .setDelay(5)
                        .setAttackInterval(3),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(121, 117));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, DOUBLE_STAB.radius, DOUBLE_STAB.distance);
        scheduleDamage(player, target);
    }
}
