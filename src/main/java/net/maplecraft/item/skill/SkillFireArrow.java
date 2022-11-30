package net.maplecraft.item.skill;

import net.maplecraft.entities.FireArrowEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.FIRE_ARROW;

public class SkillFireArrow extends SkillItem {
    public static String itemName = "skill_fire_arrow";
    public SkillFireArrow() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.WIZARD_FP)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(FIRE_ARROW.skillID)
                        .damage(FIRE_ARROW.damage)
                        .attackCount(FIRE_ARROW.attackCount)
                        .manaCost(FIRE_ARROW.manaCost)
                        .isMagic(true),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(5)
                        .textureSize(56, 77));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, FIRE_ARROW.radius, FIRE_ARROW.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new FireArrowEntity(player.level, player);
    }
}
