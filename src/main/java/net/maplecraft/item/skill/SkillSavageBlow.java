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
                        .setJobReq(JobCategory.BANDIT)
                        .setWeaponReq(EquipCategory.DAGGER)
                        .setSkillID(SAVAGE_BLOW.skillID)
                        .setDamage(SAVAGE_BLOW.damage)
                        .setAttackCount(SAVAGE_BLOW.attackCount)
                        .setManaCost(SAVAGE_BLOW.manaCost)
                        .setDelay(2)
                        .setAttackInterval(2),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(6)
                        .setTextureSize(47, 59));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, SAVAGE_BLOW.radius, SAVAGE_BLOW.distance);
        scheduleDamage(player, target);
    }
}
