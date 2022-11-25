package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillMagicClaw extends SkillItem {
    public static String itemName = "skill_magic_claw";
    public static int skillID = 2001005;
    public SkillMagicClaw() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.MAGICIAN)
                        .weaponReq(EquipCategory.WAND)
                        .damage(90)
                        .attackCount(2)
                        .manaCost(4)
                        .isMagic(true),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(94, 95));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 3, 9);
            if (!target.isEmpty()) {
                scheduleDamage(player, target);
            }
        }
    }
}
