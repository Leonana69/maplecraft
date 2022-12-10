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
                        .jobReq(JobCategory.WIZARD_FP)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(POISON_BRACE.skillID)
                        .damage(POISON_BRACE.damage)
                        .attackCount(POISON_BRACE.attackCount)
                        .manaCost(POISON_BRACE.manaCost)
                        .isMagic(true),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .textureSize(136, 124));
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
