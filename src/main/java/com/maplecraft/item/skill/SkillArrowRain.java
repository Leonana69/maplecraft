package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillArrowRain extends SkillItem {
    public static String itemName = "skill_arrow_rain";
    public SkillArrowRain() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.RANGER)
                        .setWeaponReq(EquipCategory.BOW)
                        .setSkillID(AllSkillKeyValues.ARROW_RAIN.skillId)
                        .setDamage(AllSkillKeyValues.ARROW_RAIN.damage)
                        .setAttackCount(AllSkillKeyValues.ARROW_RAIN.attackCount)
                        .setManaCost(AllSkillKeyValues.ARROW_RAIN.manaCost)
                        .setCooldown(AllSkillKeyValues.ARROW_RAIN.cooldown)
                        .setDelay(10),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(101, 133));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, AllSkillKeyValues.ARROW_RAIN.radius, AllSkillKeyValues.ARROW_RAIN.distance, true);
        scheduleDamage(player, target);
    }
}
