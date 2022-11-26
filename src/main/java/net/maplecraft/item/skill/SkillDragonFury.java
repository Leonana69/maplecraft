package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillDragonFury extends SkillItem {
    public static String itemName = "skill_dragon_fury";
    public static int skillID = 1311003;
    public SkillDragonFury() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.WARRIOR)
                        .weaponReq(EquipCategory.SPEAR)
                        .damage(120)
                        .manaCost(4),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(8)
                        .textureSize(163, 273));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, 3, 4);
            scheduleDamage(player, target);
        }
    }
}
