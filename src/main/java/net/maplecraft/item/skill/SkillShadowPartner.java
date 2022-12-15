package net.maplecraft.item.skill;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.utils.AllSkillKeyValues.SHADOW_PARTNER;

public class SkillShadowPartner extends SkillItem {
    public static String itemName = "skill_shadow_partner";
    public SkillShadowPartner() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.HERMIT)
                        .setWeaponReq(EquipCategory.CLAW)
                        .setSkillID(SHADOW_PARTNER.skillID)
                        .setDamage(SHADOW_PARTNER.damage)
                        .setAttackCount(SHADOW_PARTNER.attackCount)
                        .setManaCost(SHADOW_PARTNER.manaCost)
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
