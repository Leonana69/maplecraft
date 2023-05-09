package com.maplecraft.item.skill;

import com.maplecraft.entity.projectile.BombArrowEntity;
import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillArrowBomb extends SkillItem {
    public static String itemName = "skill_arrow_bomb";
    public SkillArrowBomb() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.HUNTER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setSkillID(AllSkillKeyValues.ARROW_BOMB.skillId)
                        .setDamage(AllSkillKeyValues.ARROW_BOMB.damage)
                        .setAttackCount(AllSkillKeyValues.ARROW_BOMB.attackCount)
                        .setManaCost(AllSkillKeyValues.ARROW_BOMB.manaCost)
                        .setCooldown(AllSkillKeyValues.ARROW_BOMB.cooldown),
                new SkillEffectInstance());
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, AllSkillKeyValues.ARROW_BOMB.radius, AllSkillKeyValues.ARROW_BOMB.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new BombArrowEntity(player.level, player);
    }
}
