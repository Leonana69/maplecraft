package net.maplecraft.item.skill;

import net.maplecraft.entities.ElementCompositionFPEntity;
import net.maplecraft.entities.FireArrowEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ELEMENT_COMPOSITION_FP;

public class SkillElementCompositionFP extends SkillItem {
    public static String itemName = "skill_element_composition_fp";
    public SkillElementCompositionFP() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.MAGE_FP)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(ELEMENT_COMPOSITION_FP.skillID)
                        .damage(ELEMENT_COMPOSITION_FP.damage)
                        .attackCount(ELEMENT_COMPOSITION_FP.attackCount)
                        .manaCost(ELEMENT_COMPOSITION_FP.manaCost)
                        .isMagic(true),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(65, 75));
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
