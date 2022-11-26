package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillArrowBlow extends SkillItem {
    public static String itemName = "skill_arrow_blow";
    public static int skillID = 3001004;
    public SkillArrowBlow() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.BOWMAN)
                        .weaponReq(EquipCategory.BOW)
                        .damage(180)
                        .manaCost(4),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(2)
                        .textureSize(113, 134));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 4, 15);
            scheduleProjectile(player, target);
        }
    }
}
