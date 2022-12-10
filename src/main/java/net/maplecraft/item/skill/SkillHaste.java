package net.maplecraft.item.skill;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.utils.AllSkillKeyValues.HASTE;

public class SkillHaste extends SkillItem {
    public static String itemName = "skill_haste";
    public SkillHaste() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.ROGUE)
                        .weaponReq(EquipCategory.CLAW)
                        .weaponReq(EquipCategory.DAGGER)
                        .skillID(HASTE.skillID)
                        .damage(HASTE.damage)
                        .attackCount(HASTE.attackCount)
                        .manaCost(HASTE.manaCost),
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
