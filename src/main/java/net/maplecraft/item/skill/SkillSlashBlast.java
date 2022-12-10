package net.maplecraft.item.skill;

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
                        .jobReq(JobCategory.WARRIOR)
                        .weaponReq(EquipCategory.SWORD)
                        .weaponReq(EquipCategory.SPEAR)
                        .skillID(SLASH_BLAST.skillID)
                        .damage(SLASH_BLAST.damage)
                        .attackCount(SLASH_BLAST.attackCount)
                        .manaCost(SLASH_BLAST.manaCost)
                        .healthCost(1),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(2)
                        .textureSize(122, 99));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, SLASH_BLAST.radius, SLASH_BLAST.distance);
        scheduleDamage(player, target);
    }
}
