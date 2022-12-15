package net.maplecraft.item.skill;

import net.maplecraft.entity.projectile.HolyArrowEntity;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.SkillItem;
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
                        .setJobReq(JobCategory.CLERIC)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(HOLY_ARROW.skillID)
                        .setDamage(HOLY_ARROW.damage)
                        .setAttackCount(HOLY_ARROW.attackCount)
                        .setManaCost(HOLY_ARROW.manaCost)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(136, 136));
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
