package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class SkillShadowPartner extends SkillItem {
    public static String itemName = "skill_shadow_partner";
    public SkillShadowPartner() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.HERMIT)
                        .setWeaponReq(EquipCategory.CLAW)
                        .setSkillID(AllSkillKeyValues.SHADOW_PARTNER.skillId)
                        .setDamage(AllSkillKeyValues.SHADOW_PARTNER.damage)
                        .setAttackCount(AllSkillKeyValues.SHADOW_PARTNER.attackCount)
                        .setManaCost(AllSkillKeyValues.SHADOW_PARTNER.manaCost)
                        .setCooldown(AllSkillKeyValues.SHADOW_PARTNER.cooldown)
                        .setDelay(4),
                new SkillEffectInstance());
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_SHADOW_PARTNER.get(),
                120 * 20, // duration in tick
                0,
                false, true));
    }
}
