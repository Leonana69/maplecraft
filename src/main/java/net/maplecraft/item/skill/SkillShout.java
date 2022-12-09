package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.SHOUT;

public class SkillShout extends SkillItem {
    public static String itemName = "skill_shout";
    public SkillShout() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.CRUSADER)
                        .weaponReq(EquipCategory.SWORD)
                        .skillID(SHOUT.skillID)
                        .damage(SHOUT.damage)
                        .attackCount(SHOUT.attackCount)
                        .manaCost(SHOUT.manaCost)
                        .delay(14),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(2)
                        .textureSize(70, 68));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, SHOUT.radius, SHOUT.distance);
        scheduleDamage(player, target);
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
    }
}
