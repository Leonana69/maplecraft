package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class SkillIronWill extends SkillItem {
    public static String itemName = "skill_iron_will";
    public SkillIronWill() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.SPEARMAN)
                        .setWeaponReq(EquipCategory.NONE)
                        .setSkillID(AllSkillKeyValues.IRON_WILL.skillId)
                        .setDamage(AllSkillKeyValues.IRON_WILL.damage)
                        .setAttackCount(AllSkillKeyValues.IRON_WILL.attackCount)
                        .setManaCost(AllSkillKeyValues.IRON_WILL.manaCost)
                        .setCooldown(AllSkillKeyValues.IRON_WILL.cooldown),
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
