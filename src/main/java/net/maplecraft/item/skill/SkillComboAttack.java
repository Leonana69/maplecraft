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
                        .jobReq(JobCategory.CRUSADER)
                        .weaponReq(EquipCategory.SWORD)
                        .skillID(COMBO_ATTACK.skillID)
                        .damage(COMBO_ATTACK.damage)
                        .attackCount(COMBO_ATTACK.attackCount)
                        .manaCost(COMBO_ATTACK.manaCost),
                new SkillHitEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_COMBO_ATTACK.get(),
                60 * 20, // duration in tick
                0,
                false, true));
    }
}