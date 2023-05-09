package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.EquipCategory;
import com.maplecraft.utils.JobCategory;
import com.maplecraft.utils.SkillBaseData;
import com.maplecraft.utils.SkillEffectInstance;
import com.maplecraft.utils.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import static com.maplecraft.utils.AllSkillKeyValues.ASSAULTER;

public class SkillAssaulter extends SkillItem {
    public static String itemName = "skill_assaulter";
    public SkillAssaulter() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CHIEF_BANDIT)
                        .setWeaponReq(EquipCategory.DAGGER)
                        .setSkillID(ASSAULTER.skillId)
                        .setDamage(ASSAULTER.damage)
                        .setAttackCount(ASSAULTER.attackCount)
                        .setManaCost(ASSAULTER.manaCost)
                        .setManaCost(ASSAULTER.manaCost),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(242, 51));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, ASSAULTER.radius, ASSAULTER.distance);
        scheduleDamage(player, target);

        Vec3 pos;
        if (target.isEmpty()) {
            pos = player.getViewVector(0);
        } else {
            LivingEntity entity = target.get(0);
            pos = new Vec3(entity.getX() - player.getX(), entity.getY() - player.getY(), entity.getZ() - player.getZ());
            pos = pos.normalize();
        }

        pos = player.position().add(pos.scale(ASSAULTER.distance * 2));
        pos = new Vec3(Math.round(pos.x), Math.floor(pos.y), Math.round(pos.z));
        for (int i = 0; i < 3; i++) {
            if (player.level.isEmptyBlock(new BlockPos(pos))) {
                player.moveTo(pos);
                return;
            }
            pos = pos.add(0, 1, 0);
        }
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        if (player.getRandom().nextFloat() < 0.3F)
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2));
    }
}
