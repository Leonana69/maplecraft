package net.maplecraft.item.skill;

import net.maplecraft.init.CustomEffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class SkillHaste extends SkillItem {
    public static String itemName = "skill_haste";
    public static int skillID = 4101004;
    public SkillHaste() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.ASSASSIN)
                        .weaponReq(EquipCategory.CLAW)
                        .damage(0)
                        .manaCost(3),
                new SkillHitEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            player.addEffect(new MobEffectInstance(
                    CustomEffectsInit.BUFF_ATTACK_SPEED_BOOST.get(),
                    60 * 20, // duration in tick
                    15,
                    false, true));
        }
    }
}
