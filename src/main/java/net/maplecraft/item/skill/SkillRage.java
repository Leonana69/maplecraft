package net.maplecraft.item.skill;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.utils.AllSkillKeyValues.RAGE;

public class SkillRage extends SkillItem {
    public static String itemName = "skill_rage";
    public SkillRage() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.FIGHTER)
                        .weaponReq(EquipCategory.SWORD)
                        .weaponReq(EquipCategory.SPEAR)
                        .skillID(RAGE.skillID)
                        .damage(RAGE.damage)
                        .attackCount(RAGE.attackCount)
                        .manaCost(RAGE.manaCost),
                new SkillHitEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_RAGE.get(),
                60 * 20, // duration in tick
                10,
                false, true));
    }
}
