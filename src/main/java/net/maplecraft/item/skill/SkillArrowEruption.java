package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
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
                        .setJobReq(JobCategory.SNIPER)
                        .setWeaponReq(EquipCategory.CROSSBOW)
                        .setSkillID(ARROW_ERUPTION.skillID)
                        .setDamage(ARROW_ERUPTION.damage)
                        .setAttackCount(ARROW_ERUPTION.attackCount)
                        .setManaCost(ARROW_ERUPTION.manaCost)
                        .setDelay(10),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(3)
                        .setTextureSize(115, 184));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, ARROW_ERUPTION.radius, ARROW_ERUPTION.distance, true);
        scheduleDamage(player, target);
    }
}
