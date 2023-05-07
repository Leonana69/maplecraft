package com.maplecraft.entity.monster;

import com.maplecraft.entity.MapleMobEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BlueSnailEntity extends MapleMobEntity {
    public static String entityName = "blue_snail_entity";

    public BlueSnailEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world, entityName);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0F)
                .add(Attributes.ATTACK_SPEED, 0.4F)
                .add(Attributes.FOLLOW_RANGE, 8F)
                .add(Attributes.MOVEMENT_SPEED, 0.12F);
    }
}
