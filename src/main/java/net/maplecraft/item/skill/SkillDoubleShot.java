package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillDoubleShot extends SkillItem {
    public static String itemName = "skill_double_shot";
    public static int skillID = 3001005;
    public SkillDoubleShot() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.BOWMAN)
                        .weaponReq(EquipCategory.BOW)
                        .damage(100)
                        .attackCount(2)
                        .attackInterval(4)
                        .manaCost(3),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(6)
                        .textureSize(70, 72));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 4, 15);
            scheduleProjectile(player, target);
        }
    }

    @Override
    public String getSKillSound() {
        return "maplecraft:sound_attack_bow";
    }
}
