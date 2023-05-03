package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.entity.projectile.AvengerEntity;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public class SkillAvenger extends SkillItem {
    public static String itemName = "skill_avenger";
    public SkillAvenger() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.HERMIT)
                        .setWeaponReq(EquipCategory.CLAW)
                        .setSkillID(AllSkillKeyValues.AVENGER.skillID)
                        .setDamage(AllSkillKeyValues.AVENGER.damage)
                        .setAttackCount(AllSkillKeyValues.AVENGER.attackCount)
                        .setManaCost(AllSkillKeyValues.AVENGER.manaCost),
                new SkillEffectInstance());
        this.consumeProjectile = true;
        this.projectilePierceLevel = 3;
    }

    @Override
    public void skillEffect(Player player) {
        scheduleProjectile(player, new ArrayList<>());
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        MapleProjectileEntity entity = new AvengerEntity(player.level, player);
        entity.setRotate(true);
        return entity;
    }
}
