package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillSlashBlast extends SkillItem {
    public static String itemName = "skill_slash_blast";
    public static int skillID = 1001005;
    public SkillSlashBlast() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.WARRIOR)
                        .weaponReq(EquipCategory.SWORD)
                        .damage(100)
                        .manaCost(3)
                        .healthCost(1),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(2)
                        .textureSize(122, 99));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, 3, 2);
            if (!target.isEmpty()) {
                scheduleDamage(player, target);
            }
        }
    }
}
