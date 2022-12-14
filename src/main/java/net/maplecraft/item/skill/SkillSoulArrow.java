package net.maplecraft.item.skill;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.utils.AllSkillKeyValues.SOUL_ARROW;

public class SkillSoulArrow extends SkillItem {
    public static String itemName = "skill_soul_arrow";
    public SkillSoulArrow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ARCHER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(SOUL_ARROW.skillID)
                        .setDamage(SOUL_ARROW.damage)
                        .setAttackCount(SOUL_ARROW.attackCount)
                        .setManaCost(SOUL_ARROW.manaCost),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_SOUL_ARROW.get(),
                120 * 20, // duration in tick
                0,
                false, true));
    }
}
