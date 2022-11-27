package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ARROW_RAIN;

public class SkillArrowRain extends SkillItem {
    public static String itemName = "skill_arrow_rain";
    public SkillArrowRain() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.RANGER)
                        .weaponReq(EquipCategory.BOW)
                        .skillID(ARROW_RAIN.skillID)
                        .damage(ARROW_RAIN.damage)
                        .attackCount(ARROW_RAIN.attackCount)
                        .manaCost(ARROW_RAIN.manaCost)
                        .delay(10),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(101, 133));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, ARROW_RAIN.radius, ARROW_RAIN.distance, true);
            scheduleDamage(player, target);
        }
    }
}
