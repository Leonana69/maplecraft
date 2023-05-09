package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class SkillHaste extends SkillItem {
    public static String itemName = "skill_haste";
    public SkillHaste() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ROGUE)
                        .setWeaponReq(EquipCategory.CLAW)
                        .setWeaponReq(EquipCategory.DAGGER)
                        .setSkillID(AllSkillKeyValues.HASTE.skillId)
                        .setDamage(AllSkillKeyValues.HASTE.damage)
                        .setAttackCount(AllSkillKeyValues.HASTE.attackCount)
                        .setManaCost(AllSkillKeyValues.HASTE.manaCost)
                        .setCooldown(AllSkillKeyValues.HASTE.cooldown),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_HASTE.get(),
                120 * 20, // duration in tick
                20,
                false, true));
    }
}
