package net.maplecraft.item.skill;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.utils.AllSkillKeyValues.COMBO_ATTACK;

public class SkillComboAttack extends SkillItem {
    public static String itemName = "skill_combo_attack";
    public SkillComboAttack() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CRUSADER)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setSkillID(COMBO_ATTACK.skillID)
                        .setDamage(COMBO_ATTACK.damage)
                        .setAttackCount(COMBO_ATTACK.attackCount)
                        .setManaCost(COMBO_ATTACK.manaCost),
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
