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
                        .setJobReq(JobCategory.RANGER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setSkillID(ARROW_RAIN.skillID)
                        .setDamage(ARROW_RAIN.damage)
                        .setAttackCount(ARROW_RAIN.attackCount)
                        .setManaCost(ARROW_RAIN.manaCost)
                        .setDelay(10),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(101, 133));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, ARROW_RAIN.radius, ARROW_RAIN.distance, true);
        scheduleDamage(player, target);
    }
}
