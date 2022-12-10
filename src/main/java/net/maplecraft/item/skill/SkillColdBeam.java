package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.COLD_BEAM;

public class SkillColdBeam extends SkillItem {
    public static String itemName = "skill_cold_beam";
    public SkillColdBeam() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.WIZARD_IL)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(COLD_BEAM.skillID)
                        .damage(COLD_BEAM.damage)
                        .attackCount(COLD_BEAM.attackCount)
                        .manaCost(COLD_BEAM.manaCost)
                        .isMagic(true),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .delay(2)
                        .hitEffectOnHit(false)
                        .textureSize(79, 179));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, COLD_BEAM.radius, COLD_BEAM.distance);
        if (!target.isEmpty()) {
            scheduleDamage(player, target);
        }
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4));
    }
}
