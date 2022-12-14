package net.maplecraft.item.skill;

import net.maplecraft.entity.projectile.BombArrowEntity;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ARROW_BOMB;

public class SkillArrowBomb extends SkillItem {
    public static String itemName = "skill_arrow_bomb";
    public SkillArrowBomb() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.HUNTER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setSkillID(ARROW_BOMB.skillID)
                        .setDamage(ARROW_BOMB.damage)
                        .setAttackCount(ARROW_BOMB.attackCount)
                        .setManaCost(ARROW_BOMB.manaCost),
                new SkillEffectInstance());
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, ARROW_BOMB.radius, ARROW_BOMB.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new BombArrowEntity(player.level, player);
    }
}
