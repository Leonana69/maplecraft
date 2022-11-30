package net.maplecraft.item.skill;

import net.maplecraft.entities.HolyArrowEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.HOLY_ARROW;

public class SkillHolyArrow extends SkillItem {
    public static String itemName = "skill_holy_arrow";
    public SkillHolyArrow() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.CLERIC)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(HOLY_ARROW.skillID)
                        .damage(HOLY_ARROW.damage)
                        .attackCount(HOLY_ARROW.attackCount)
                        .manaCost(HOLY_ARROW.manaCost)
                        .isMagic(true),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(136, 136));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, HOLY_ARROW.radius, HOLY_ARROW.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new HolyArrowEntity(player.level, player);
    }
}
