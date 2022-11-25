package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillArrowBomb extends SkillItem {
    public static String itemName = "skill_arrow_bomb";
    public static int skillID = 3101005;
    public SkillArrowBomb() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.HUNTER)
                        .weaponReq(EquipCategory.BOW)
                        .damage(200)
                        .manaCost(4),
                new SkillHitEffectInstance());
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
