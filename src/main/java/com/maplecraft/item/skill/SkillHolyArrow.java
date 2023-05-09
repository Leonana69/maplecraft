package com.maplecraft.item.skill;

import com.maplecraft.entity.projectile.HolyArrowEntity;
import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillHolyArrow extends SkillItem {
    public static String itemName = "skill_holy_arrow";
    public SkillHolyArrow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CLERIC)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(AllSkillKeyValues.HOLY_ARROW.skillId)
                        .setDamage(AllSkillKeyValues.HOLY_ARROW.damage)
                        .setAttackCount(AllSkillKeyValues.HOLY_ARROW.attackCount)
                        .setManaCost(AllSkillKeyValues.HOLY_ARROW.manaCost)
                        .setCooldown(AllSkillKeyValues.HOLY_ARROW.cooldown)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(136, 136));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.HOLY_ARROW.radius, AllSkillKeyValues.HOLY_ARROW.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new HolyArrowEntity(player.level, player);
    }
}
