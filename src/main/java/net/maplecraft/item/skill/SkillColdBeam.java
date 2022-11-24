package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillColdBeam extends SkillItem {
    public static String itemName = "skill_cold_beam";
    public static int skillID = 2201004;
    public SkillColdBeam() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.ICE_LIGHTNING)
                        .weaponReq(EquipCategory.WAND)
                        .damage(150)
                        .manaCost(3)
                        .isMagic(true),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .delay(2)
                        .hitEffectOnHit(false)
                        .textureSize(79, 179));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 3, 7);
            if (!target.isEmpty()) {
                scheduleDamage(player, target);
            }
        }
    }

    @Override
    public void onHitEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4));
    }
}
