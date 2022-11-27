package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.DOUBLE_SHOT;

public class SkillDoubleShot extends SkillItem {
    public static String itemName = "skill_double_shot";
    public SkillDoubleShot() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.BOWMAN)
                        .weaponReq(EquipCategory.BOW)
                        .skillID(DOUBLE_SHOT.skillID)
                        .damage(DOUBLE_SHOT.damage)
                        .attackCount(DOUBLE_SHOT.attackCount)
                        .manaCost(DOUBLE_SHOT.manaCost)
                        .attackInterval(4),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .textureSize(70, 72));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, DOUBLE_SHOT.radius, DOUBLE_SHOT.distance);
            scheduleProjectile(player, target);
        }
    }

    @Override
    public String getSKillSound() {
        return "maplecraft:sound_attack_bow";
    }
}
