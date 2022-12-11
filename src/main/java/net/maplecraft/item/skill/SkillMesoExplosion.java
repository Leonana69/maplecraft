package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
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
                        .delay(20),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(127, 128));
    }

    @Override
    public void skillEffect(Player player) {
        Vec3 base = player.getPosition(0);
        double radius = MESO_EXPLOSION.radius;
        AABB box = new AABB(
                base.x - radius / 2,
                base.y - radius / 2,
                base.z - radius / 2,
                base.x + radius / 2,
                base.y + radius / 2,
                base.z + radius / 2);
        List<Entity> list = new ArrayList<>(player.level.getEntitiesOfClass(ItemEntity.class, box));
        for (Entity entity : list) {
            System.out.println(entity.getName());
        }

        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, MESO_EXPLOSION.radius, MESO_EXPLOSION.distance, true);
        scheduleDamage(player, target);
    }
}
