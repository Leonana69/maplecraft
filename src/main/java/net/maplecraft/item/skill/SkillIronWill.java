package net.maplecraft.item.skill;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.utils.AllSkillKeyValues.IRON_WILL;

public class SkillIronWill extends SkillItem {
    public static String itemName = "skill_iron_will";
    public SkillIronWill() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.SPEARMAN)
                        .setWeaponReq(EquipCategory.NONE)
                        .setSkillID(IRON_WILL.skillID)
                        .setDamage(IRON_WILL.damage)
                        .setAttackCount(IRON_WILL.attackCount)
                        .setManaCost(IRON_WILL.manaCost),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_IRON_WILL.get(),
                120 * 20, // duration in tick
                15,
                false, true));
    }
}
