package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

import static net.maplecraft.utils.AllSkillKeyValues.IRON_ARROW;

public class SkillIronArrow extends SkillItem {
    public static String itemName = "skill_iron_arrow";
    public SkillIronArrow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CROSSBOWMAN)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(IRON_ARROW.skillID)
                        .setDamage(IRON_ARROW.damage)
                        .setAttackCount(IRON_ARROW.attackCount)
                        .setManaCost(IRON_ARROW.manaCost),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(63, 106));
        this.consumeProjectile = true;
        this.projectilePierceLevel = 6;
    }

    @Override
    public void skillEffect(Player player) {
        scheduleProjectile(player, new ArrayList<>());
    }
}
