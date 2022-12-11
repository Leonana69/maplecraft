package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ASSAULTER;

public class SkillAssaulter extends SkillItem {
    public static String itemName = "skill_assaulter";
    public SkillAssaulter() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.CHIEF_BANDIT)
                        .weaponReq(EquipCategory.DAGGER)
                        .skillID(ASSAULTER.skillID)
                        .damage(ASSAULTER.damage)
                        .attackCount(ASSAULTER.attackCount)
                        .manaCost(ASSAULTER.manaCost),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(242, 51));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, ASSAULTER.radius, ASSAULTER.distance);
        scheduleDamage(player, target);

        Vec3 pos;
        if (target.isEmpty()) {
            pos = player.position().add(player.getViewVector(0).scale(ASSAULTER.distance * 2));
        } else {
            LivingEntity entity = target.get(0);
            pos = new Vec3(entity.getX() - player.getX(), entity.getY() - player.getY(), entity.getZ() - player.getZ());
            pos = player.position().add(pos.normalize().scale(ASSAULTER.distance * 2));
        }

        pos = new Vec3(Math.round(pos.x), Math.floor(pos.y), Math.round(pos.z));
        for (int i = 0; i < 2; i++) {
            if (player.level.isEmptyBlock(new BlockPos(pos))) {
                player.moveTo(pos);
                return;
            }
            pos = pos.add(0, 1, 0);
        }
    }
}
