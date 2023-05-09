package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillArrowEruption extends SkillItem {
    public static String itemName = "skill_arrow_eruption";
    public SkillArrowEruption() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.SNIPER)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(AllSkillKeyValues.ARROW_ERUPTION.skillId)
                        .setDamage(AllSkillKeyValues.ARROW_ERUPTION.damage)
                        .setAttackCount(AllSkillKeyValues.ARROW_ERUPTION.attackCount)
                        .setManaCost(AllSkillKeyValues.ARROW_ERUPTION.manaCost)
                        .setCooldown(AllSkillKeyValues.ARROW_ERUPTION.cooldown)
                        .setDelay(10),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(115, 184));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, AllSkillKeyValues.ARROW_ERUPTION.radius, AllSkillKeyValues.ARROW_ERUPTION.distance, true);
        scheduleDamage(player, target);
    }
}
