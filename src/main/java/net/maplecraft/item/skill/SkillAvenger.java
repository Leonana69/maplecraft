package net.maplecraft.item.skill;

import net.maplecraft.entity.projectile.AvengerEntity;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

import static net.maplecraft.utils.AllSkillKeyValues.AVENGER;

public class SkillAvenger extends SkillItem {
    public static String itemName = "skill_avenger";
    public SkillAvenger() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.HERMIT)
                        .setWeaponReq(EquipCategory.CLAW)
                        .setSkillID(AVENGER.skillID)
                        .setDamage(AVENGER.damage)
                        .setAttackCount(AVENGER.attackCount)
                        .setManaCost(AVENGER.manaCost),
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
