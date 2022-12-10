package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.THUNDERBOLT;

public class SkillThunderbolt extends SkillItem {
    public static String itemName = "skill_thunderbolt";
    public SkillThunderbolt() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.WIZARD_IL)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(THUNDERBOLT.skillID)
                        .damage(THUNDERBOLT.damage)
                        .attackCount(THUNDERBOLT.attackCount)
                        .manaCost(THUNDERBOLT.manaCost)
                        .delay(10)
                        .isMagic(true),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(8)
                        .textureSize(68, 219));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, THUNDERBOLT.radius, THUNDERBOLT.distance, true);
        scheduleDamage(player, target);
    }
}
