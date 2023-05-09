package com.maplecraft.item.skill;

import com.maplecraft.entity.projectile.FireArrowEntity;
import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillFireArrow extends SkillItem {
    public static String itemName = "skill_fire_arrow";
    public SkillFireArrow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WIZARD_FP)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(AllSkillKeyValues.FIRE_ARROW.skillId)
                        .setDamage(AllSkillKeyValues.FIRE_ARROW.damage)
                        .setAttackCount(AllSkillKeyValues.FIRE_ARROW.attackCount)
                        .setManaCost(AllSkillKeyValues.FIRE_ARROW.manaCost)
                        .setCooldown(AllSkillKeyValues.FIRE_ARROW.cooldown)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(5)
                        .setTextureSize(56, 77));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.FIRE_ARROW.radius, AllSkillKeyValues.FIRE_ARROW.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new FireArrowEntity(player.level, player);
    }
}
