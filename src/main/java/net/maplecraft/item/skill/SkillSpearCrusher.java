package net.maplecraft.item.skill;

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
                        .jobReq(JobCategory.DRAGON_KNIGHT)
                        .weaponReq(EquipCategory.SPEAR)
                        .skillID(SPEAR_CRUSHER.skillID)
                        .damage(SPEAR_CRUSHER.damage)
                        .attackCount(SPEAR_CRUSHER.attackCount)
                        .manaCost(SPEAR_CRUSHER.manaCost)
                        .delay(12)
                        .attackInterval(3),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(120, 107));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, SPEAR_CRUSHER.radius, SPEAR_CRUSHER.distance, true);
        scheduleDamage(player, target);
    }
}
