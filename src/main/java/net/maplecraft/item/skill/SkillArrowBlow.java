package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ARROW_BLOW;

public class SkillArrowBlow extends SkillItem {
    public static String itemName = "skill_arrow_blow";
    public SkillArrowBlow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ARCHER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(ARROW_BLOW.skillID)
                        .setDamage(ARROW_BLOW.damage)
                        .setAttackCount(ARROW_BLOW.attackCount)
                        .setManaCost(ARROW_BLOW.manaCost),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(2)
                        .setTextureSize(113, 134));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, ARROW_BLOW.radius, ARROW_BLOW.distance);
        scheduleProjectile(player, target);
    }
}
