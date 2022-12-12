package net.maplecraft.item.skill;

import net.maplecraft.entities.ElementCompositionILEntity;
import net.maplecraft.entities.FireArrowEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ELEMENT_COMPOSITION_IL;

public class SkillElementCompositionIL extends SkillItem {
    public static String itemName = "skill_element_composition_il";
    public SkillElementCompositionIL() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.MAGE_IL)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(ELEMENT_COMPOSITION_IL.skillID)
                        .damage(ELEMENT_COMPOSITION_IL.damage)
                        .attackCount(ELEMENT_COMPOSITION_IL.attackCount)
                        .manaCost(ELEMENT_COMPOSITION_IL.manaCost)
                        .isMagic(true),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(64, 75));
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
