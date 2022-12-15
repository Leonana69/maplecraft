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
                        .setJobReq(JobCategory.ARCHER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(STRAFE.skillID)
                        .setDamage(STRAFE.damage)
                        .setAttackCount(STRAFE.attackCount)
                        .setManaCost(STRAFE.manaCost)
                        .setAttackInterval(3),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(103, 97));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, STRAFE.radius, STRAFE.distance);
        scheduleProjectile(player, target);
    }
}
