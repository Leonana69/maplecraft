package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.DRAGON_ROAR;

public class SkillDragonRoar extends SkillItem {
    public static String itemName = "skill_dragon_roar";
    public SkillDragonRoar() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.DRAGON_KNIGHT)
                        .weaponReq(EquipCategory.SPEAR)
                        .skillID(DRAGON_ROAR.skillID)
                        .damage(DRAGON_ROAR.damage)
                        .attackCount(DRAGON_ROAR.attackCount)
                        .manaCost(DRAGON_ROAR.manaCost)
                        .healthCost(4)
                        .delay(14),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(104, 105));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, DRAGON_ROAR.radius, DRAGON_ROAR.distance, true);
        scheduleDamage(player, target);
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
    }
}
