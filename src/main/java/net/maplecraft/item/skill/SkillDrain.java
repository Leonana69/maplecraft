package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillDrain extends SkillItem {
    public static String itemName = "skill_drain";
    public static int skillID = 4101005;
    public SkillDrain() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.ASSASSIN)
                        .weaponReq(EquipCategory.CLAW)
                        .damage(120)
                        .manaCost(4),
                new SkillHitEffectInstance());
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 4, 15);
            scheduleProjectile(player, target);
        }
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        player.setHealth(player.getHealth() + 2);
    }

    @Override
    public String getSKillSound() {
        return "maplecraft:sound_claw_attack";
    }
}
