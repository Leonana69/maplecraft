package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
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
                        .setJobReq(JobCategory.ARCHER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(DOUBLE_SHOT.skillID)
                        .setDamage(DOUBLE_SHOT.damage)
                        .setAttackCount(DOUBLE_SHOT.attackCount)
                        .setManaCost(DOUBLE_SHOT.manaCost)
                        .setAttackInterval(4),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(6)
                        .setTextureSize(70, 72));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, DOUBLE_SHOT.radius, DOUBLE_SHOT.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public String getSKillSound() {
        return "maplecraft:sound_attack_bow";
    }
}
