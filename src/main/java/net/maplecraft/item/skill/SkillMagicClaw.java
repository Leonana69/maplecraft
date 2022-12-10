package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.MAGIC_CLAW;

public class SkillMagicClaw extends SkillItem {
    public static String itemName = "skill_magic_claw";
    public SkillMagicClaw() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.MAGICIAN)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(MAGIC_CLAW.skillID)
                        .damage(MAGIC_CLAW.damage)
                        .attackCount(MAGIC_CLAW.attackCount)
                        .manaCost(MAGIC_CLAW.attackCount)
                        .isMagic(true),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(94, 95));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, MAGIC_CLAW.radius, MAGIC_CLAW.distance);
        scheduleDamage(player, target);
    }
}
