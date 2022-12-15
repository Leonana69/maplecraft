package net.maplecraft.item.skill;

import net.maplecraft.entities.PoisonBraceEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.POISON_BRACE;

public class SkillPoisonBrace extends SkillItem {
    public static String itemName = "skill_poison_brace";
    public SkillPoisonBrace() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WIZARD_FP)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(POISON_BRACE.skillID)
                        .setDamage(POISON_BRACE.damage)
                        .setAttackCount(POISON_BRACE.attackCount)
                        .setManaCost(POISON_BRACE.manaCost)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(6)
                        .setTextureSize(136, 124));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, POISON_BRACE.radius, POISON_BRACE.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new PoisonBraceEntity(player.level, player);
    }
}
