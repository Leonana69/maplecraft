package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ARROW_ERUPTION;

public class SkillArrowEruption extends SkillItem {
    public static String itemName = "skill_arrow_eruption";
    public SkillArrowEruption() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.SNIPER)
                        .weaponReq(EquipCategory.CROSSBOW)
                        .skillID(ARROW_ERUPTION.skillID)
                        .damage(ARROW_ERUPTION.damage)
                        .attackCount(ARROW_ERUPTION.attackCount)
                        .manaCost(ARROW_ERUPTION.manaCost)
                        .delay(10),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(115, 184));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, ARROW_ERUPTION.radius, ARROW_ERUPTION.distance, true);
        scheduleDamage(player, target);
    }
}
