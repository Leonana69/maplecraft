package com.maplecraft.item.skill;

import com.maplecraft.entity.projectile.ElementCompositionILEntity;
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

import static com.maplecraft.utils.AllSkillKeyValues.ELEMENT_COMPOSITION_IL;

public class SkillElementCompositionIL extends SkillItem {
    public static String itemName = "skill_element_composition_il";
    public SkillElementCompositionIL() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.MAGE_IL)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(ELEMENT_COMPOSITION_IL.skillId)
                        .setDamage(ELEMENT_COMPOSITION_IL.damage)
                        .setAttackCount(ELEMENT_COMPOSITION_IL.attackCount)
                        .setManaCost(ELEMENT_COMPOSITION_IL.manaCost)
                        .setCooldown(ELEMENT_COMPOSITION_IL.cooldown)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(64, 75));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, ELEMENT_COMPOSITION_IL.radius, ELEMENT_COMPOSITION_IL.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new ElementCompositionILEntity(player.level, player);
    }
}
