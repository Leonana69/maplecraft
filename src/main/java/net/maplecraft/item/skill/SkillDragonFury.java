package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.DRAGON_FURY;

public class SkillDragonFury extends SkillItem {
    public static String itemName = "skill_dragon_fury";
    public SkillDragonFury() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.WARRIOR)
                        .weaponReq(EquipCategory.SPEAR)
                        .skillID(DRAGON_FURY.skillID)
                        .damage(DRAGON_FURY.damage)
                        .attackCount(DRAGON_FURY.attackCount)
                        .manaCost(DRAGON_FURY.manaCost),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(8)
                        .textureSize(163, 273));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, DRAGON_FURY.radius, DRAGON_FURY.distance);
            scheduleDamage(player, target);
        }
    }
}
