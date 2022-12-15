package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
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
                        .setJobReq(JobCategory.MAGICIAN)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(MAGIC_CLAW.skillID)
                        .setDamage(MAGIC_CLAW.damage)
                        .setAttackCount(MAGIC_CLAW.attackCount)
                        .setManaCost(MAGIC_CLAW.manaCost)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(94, 95));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, MAGIC_CLAW.radius, MAGIC_CLAW.distance);
        scheduleDamage(player, target);
    }
}
