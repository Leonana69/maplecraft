package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
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
                        .setJobReq(JobCategory.WARRIOR)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(DRAGON_FURY.skillID)
                        .setDamage(DRAGON_FURY.damage)
                        .setAttackCount(DRAGON_FURY.attackCount)
                        .setManaCost(DRAGON_FURY.manaCost),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(8)
                        .setTextureSize(163, 273));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, DRAGON_FURY.radius, DRAGON_FURY.distance);
        scheduleDamage(player, target);
    }
}
