package com.maplecraft.entity.boss.zakum;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class BossZakumLeftHandEntity extends BossZakumHandEntity {
    public static String entityName = "boss_zakum_left_hand_entity";
    public BossZakumLeftHandEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world, entityName);
    }
}
