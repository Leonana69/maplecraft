package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ARROW_BLOW;

public class SkillArrowBlow extends SkillItem {
    public static String itemName = "skill_arrow_blow";
    public SkillArrowBlow() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.BOWMAN)
                        .weaponReq(EquipCategory.BOW)
                        .skillID(ARROW_BLOW.skillID)
                        .damage(ARROW_BLOW.damage)
                        .attackCount(ARROW_BLOW.attackCount)
                        .manaCost(ARROW_BLOW.manaCost),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(2)
                        .textureSize(113, 134));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, ARROW_BLOW.radius, ARROW_BLOW.distance);
            scheduleProjectile(player, target);
        }
    }
}
