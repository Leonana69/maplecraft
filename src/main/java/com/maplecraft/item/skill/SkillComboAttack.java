package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class SkillComboAttack extends SkillItem {
    public static String itemName = "skill_combo_attack";
    public SkillComboAttack() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CRUSADER)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setSkillID(AllSkillKeyValues.COMBO_ATTACK.skillID)
                        .setDamage(AllSkillKeyValues.COMBO_ATTACK.damage)
                        .setAttackCount(AllSkillKeyValues.COMBO_ATTACK.attackCount)
                        .setManaCost(AllSkillKeyValues.COMBO_ATTACK.manaCost),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_COMBO_ATTACK.get(),
                120 * 20, // duration in tick
                0,
                false, true));
    }
}
