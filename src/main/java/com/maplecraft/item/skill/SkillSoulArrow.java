package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class SkillSoulArrow extends SkillItem {
    public static String itemName = "skill_soul_arrow";
    public SkillSoulArrow() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.ARCHER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(AllSkillKeyValues.SOUL_ARROW.skillId)
                        .setDamage(AllSkillKeyValues.SOUL_ARROW.damage)
                        .setAttackCount(AllSkillKeyValues.SOUL_ARROW.attackCount)
                        .setManaCost(AllSkillKeyValues.SOUL_ARROW.manaCost)
                        .setCooldown(AllSkillKeyValues.SOUL_ARROW.cooldown),
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
