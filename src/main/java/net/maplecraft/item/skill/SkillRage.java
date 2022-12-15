package net.maplecraft.item.skill;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.utils.AllSkillKeyValues.RAGE;

public class SkillRage extends SkillItem {
    public static String itemName = "skill_rage";
    public SkillRage() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.FIGHTER)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(RAGE.skillID)
                        .setDamage(RAGE.damage)
                        .setAttackCount(RAGE.attackCount)
                        .setManaCost(RAGE.manaCost),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_RAGE.get(),
                120 * 20, // duration in tick
                10,
                false, true));
    }
}
