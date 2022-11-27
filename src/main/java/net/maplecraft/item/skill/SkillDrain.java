package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.DRAIN;

public class SkillDrain extends SkillItem {
    public static String itemName = "skill_drain";
    public SkillDrain() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.ASSASSIN)
                        .weaponReq(EquipCategory.CLAW)
                        .skillID(DRAIN.skillID)
                        .damage(DRAIN.damage)
                        .attackCount(DRAIN.attackCount)
                        .manaCost(DRAIN.manaCost),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .textureSize(68, 66));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, DRAIN.radius, DRAIN.distance);
            scheduleProjectile(player, target);
        }
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        player.setHealth(player.getHealth() + 2);
    }
}
