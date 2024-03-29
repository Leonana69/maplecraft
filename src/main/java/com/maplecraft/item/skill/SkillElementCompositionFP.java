package com.maplecraft.item.skill;

import com.maplecraft.entity.projectile.ElementCompositionFPEntity;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.EquipCategory;
import com.maplecraft.utils.JobCategory;
import com.maplecraft.utils.SkillBaseData;
import com.maplecraft.utils.SkillEffectInstance;
import com.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static com.maplecraft.utils.AllSkillKeyValues.ELEMENT_COMPOSITION_FP;

public class SkillElementCompositionFP extends SkillItem {
    public static String itemName = "skill_element_composition_fp";
    public SkillElementCompositionFP() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.MAGE_FP)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(ELEMENT_COMPOSITION_FP.skillId)
                        .setDamage(ELEMENT_COMPOSITION_FP.damage)
                        .setAttackCount(ELEMENT_COMPOSITION_FP.attackCount)
                        .setManaCost(ELEMENT_COMPOSITION_FP.manaCost)
                        .setCooldown(ELEMENT_COMPOSITION_FP.cooldown)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(65, 75));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, ELEMENT_COMPOSITION_FP.radius, ELEMENT_COMPOSITION_FP.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new ElementCompositionFPEntity(player.level, player);
    }
}
