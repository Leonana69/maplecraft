package com.maplecraft.item.skill;

import com.maplecraft.init.EffectsInit;
import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.EquipCategory;
import com.maplecraft.utils.JobCategory;
import com.maplecraft.utils.SkillBaseData;
import com.maplecraft.utils.SkillEffectInstance;
import com.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static com.maplecraft.utils.AllSkillKeyValues.RAGE;

public class SkillRage extends SkillItem {
    public static String itemName = "skill_rage";
    public SkillRage() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.FIGHTER)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setWeaponReq(EquipCategory.SPEAR)
                        .setSkillID(RAGE.skillId)
                        .setDamage(RAGE.damage)
                        .setAttackCount(RAGE.attackCount)
                        .setManaCost(RAGE.manaCost)
                        .setCooldown(RAGE.cooldown),
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
