package net.maplecraft.item.skill;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.utils.AllSkillKeyValues.IRON_WILL;

public class SkillIronWill extends SkillItem {
    public static String itemName = "skill_iron_will";
    public SkillIronWill() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.SPEARMAN)
                        .weaponReq(EquipCategory.NONE)
                        .skillID(IRON_WILL.skillID)
                        .damage(IRON_WILL.damage)
                        .attackCount(IRON_WILL.attackCount)
                        .manaCost(IRON_WILL.manaCost),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        player.addEffect(new MobEffectInstance(
                EffectsInit.BUFF_IRON_WILL.get(),
                60 * 20, // duration in tick
                15,
                false, true));
    }
}
