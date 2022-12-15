package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.SPEAR_CRUSHER;

public class SkillSpearCrusher extends SkillItem {
    public static String itemName = "skill_spear_crusher";
    public SkillSpearCrusher() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.DRAGON_KNIGHT)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(SPEAR_CRUSHER.skillID)
                        .setDamage(SPEAR_CRUSHER.damage)
                        .setAttackCount(SPEAR_CRUSHER.attackCount)
                        .setManaCost(SPEAR_CRUSHER.manaCost)
                        .setDelay(12)
                        .setAttackInterval(3),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(120, 107));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, SPEAR_CRUSHER.radius, SPEAR_CRUSHER.distance, true);
        scheduleDamage(player, target);
    }
}
