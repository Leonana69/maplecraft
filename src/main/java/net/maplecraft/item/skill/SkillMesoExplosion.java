package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.MESO_EXPLOSION;

public class SkillMesoExplosion extends SkillItem {
    public static String itemName = "skill_meso_explosion";
    public SkillMesoExplosion() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.CHIEF_BANDIT)
                        .weaponReq(EquipCategory.DAGGER)
                        .skillID(MESO_EXPLOSION.skillID)
                        .damage(MESO_EXPLOSION.damage)
                        .attackCount(MESO_EXPLOSION.attackCount)
                        .manaCost(MESO_EXPLOSION.manaCost)
                        .delay(12),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(127, 128));
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
        List<Entity> itemEntityList = new ArrayList<>(player.level.getEntitiesOfClass(ItemEntity.class, box));
        List<Entity> mesoList = new ArrayList<>();
        for (Entity entity : itemEntityList) {
            if (entity.getName().getString().contains("Meso")) {
                mesoList.add(entity);
            }
        }

        radius = MESO_EXPLOSION.distance;
        int cnt = 0;
        for (Entity entity : mesoList) {
            if (cnt++ > 2)
                return;
            box = new AABB(
            entity.getX() - radius / 2,
            entity.getY() - radius / 2,
            entity.getZ() - radius / 2,
            entity.getX() + radius / 2,
            entity.getY() + radius / 2,
            entity.getZ() + radius / 2);
            List<LivingEntity> target = new ArrayList<>(player.level.getEntitiesOfClass(LivingEntity.class, box));
            if (entity.getName().getString().equals("Tiny Meso")) {
                scheduleDamage(player, target, 0.8F);
            } else if (entity.getName().getString().equals("Small Meso")) {
                scheduleDamage(player, target, 1.2F);
            } else if (entity.getName().getString().equals("Medium Meso")) {
                scheduleDamage(player, target, 2.0F);
            } else if (entity.getName().getString().equals("Large Meso")) {
                scheduleDamage(player, target, 3.0F);
            }

            if (player.level instanceof ServerLevel level)
                level.sendParticles(ParticleTypes.EXPLOSION, entity.getX(), entity.getY(), entity.getZ(), 3, 0.1, 0.1, 0.1, 0.3);
            entity.discard();
        }
    }
}
