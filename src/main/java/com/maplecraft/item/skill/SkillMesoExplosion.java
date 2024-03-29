package com.maplecraft.item.skill;

import com.maplecraft.utils.EquipCategory;
import com.maplecraft.utils.JobCategory;
import com.maplecraft.utils.SkillBaseData;
import com.maplecraft.utils.SkillEffectInstance;
import com.mojang.math.Vector3f;
import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

import static com.maplecraft.utils.AllSkillKeyValues.MESO_EXPLOSION;

public class SkillMesoExplosion extends SkillItem {
    public static String itemName = "skill_meso_explosion";
    public SkillMesoExplosion() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CHIEF_BANDIT)
                        .setWeaponReq(EquipCategory.DAGGER)
                        .setSkillID(MESO_EXPLOSION.skillId)
                        .setDamage(MESO_EXPLOSION.damage)
                        .setAttackCount(MESO_EXPLOSION.attackCount)
                        .setManaCost(MESO_EXPLOSION.manaCost)
                        .setCooldown(MESO_EXPLOSION.cooldown)
                        .setDelay(12),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(4)
                        .setTextureSize(127, 128));
    }

    @Override
    public void skillEffect(Player player) {
        double radius = MESO_EXPLOSION.radius;
        AABB box = new AABB(
                player.getX() - radius / 2,
                player.getY() - radius / 2,
                player.getZ() - radius / 2,
                player.getX() + radius / 2,
                player.getY() + radius / 2,
                player.getZ() + radius / 2);
        List<ItemEntity> itemEntityList = new ArrayList<>(player.level.getEntitiesOfClass(ItemEntity.class, box));
        List<ItemEntity> mesoList = new ArrayList<>();
        for (ItemEntity entity : itemEntityList) {
            if (entity.getName().getString().contains("Meso")) {
                mesoList.add(entity);
            }
        }

        radius = MESO_EXPLOSION.distance;
        int cnt = 0;
        for (ItemEntity entity : mesoList) {
            if (cnt >= 4)
                return;
            box = new AABB(
            entity.getX() - radius / 2,
            entity.getY() - radius / 2,
            entity.getZ() - radius / 2,
            entity.getX() + radius / 2,
            entity.getY() + radius / 2,
            entity.getZ() + radius / 2);
            List<LivingEntity> target = new ArrayList<>(player.level.getEntitiesOfClass(LivingEntity.class, box));

            for (int i = 0; i < target.size(); i++) {
                if (target.get(i) instanceof Player) {
                    target.remove(i);
                    i--;
                }
            }

            int count = Math.min(entity.getItem().getCount(), 3);
            cnt += count;

            if (entity.getName().getString().equals("Tiny Meso")) {
                scheduleDamage(player, target, 0.8F * count);
            } else if (entity.getName().getString().equals("Small Meso")) {
                scheduleDamage(player, target, 1.2F * count);
            } else if (entity.getName().getString().equals("Medium Meso")) {
                scheduleDamage(player, target, 2.0F * count);
            } else if (entity.getName().getString().equals("Large Meso")) {
                scheduleDamage(player, target, 3.0F * count);
            }

            if (player.level instanceof ServerLevel level)
                level.sendParticles(new DustParticleOptions(
                        new Vector3f(0.3F, 0.3F, 0.3F), 1.0F),
                        entity.getX(), entity.getY(), entity.getZ(),
                        40,
                        0.3, 0.6, 0.3,
                        1.5);
            entity.discard();
        }
    }
}
