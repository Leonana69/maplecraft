package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillArrowRain extends SkillItem {
    public static String itemName = "skill_arrow_rain";
    public static int skillID = 3111004;
    public SkillArrowRain() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .damage(150)
                        .delay(10)
                        .jobReq(JobCategory.RANGER)
                        .weaponReq(EquipCategory.BOW)
                        .manaCost(6),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(101, 133));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, 8, 0, true);
            scheduleDamage(player, target);
        }
    }
}
