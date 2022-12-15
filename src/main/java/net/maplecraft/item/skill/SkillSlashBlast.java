package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.SLASH_BLAST;

public class SkillSlashBlast extends SkillItem {
    public static String itemName = "skill_slash_blast";
    public SkillSlashBlast() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WARRIOR)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(SLASH_BLAST.skillID)
                        .setDamage(SLASH_BLAST.damage)
                        .setAttackCount(SLASH_BLAST.attackCount)
                        .setManaCost(SLASH_BLAST.manaCost)
                        .setHealthCost(1),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(122, 99));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, SLASH_BLAST.radius, SLASH_BLAST.distance);
        scheduleDamage(player, target);
    }
}
