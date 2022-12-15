package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.LUCKY_SEVEN;

public class SkillLuckySeven extends SkillItem {
    public static String itemName = "skill_lucky_seven";
    public SkillLuckySeven() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ROGUE)
                        .setWeaponReq(EquipCategory.CLAW)
                        .setSkillID(LUCKY_SEVEN.skillID)
                        .setDamage(LUCKY_SEVEN.damage)
                        .setAttackCount(LUCKY_SEVEN.attackCount)
                        .setManaCost(LUCKY_SEVEN.manaCost)
                        .setAttackInterval(4),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(50, 51));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, LUCKY_SEVEN.radius, LUCKY_SEVEN.distance);
        scheduleProjectile(player, target);
    }
}
