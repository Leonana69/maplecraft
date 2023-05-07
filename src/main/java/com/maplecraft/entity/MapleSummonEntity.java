package com.maplecraft.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class MapleSummonEntity extends TamableAnimal implements IAnimatable, MapleLivingEntity {
    public String entityName;
    public float scale = 1.0F;
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private int lifeTime = 0;

    public MapleSummonEntity(EntityType<? extends TamableAnimal> entityType, Level world, String entityName) {
        super(entityType, world);
        this.entityName = entityName;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        return null;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mob) {
        return null;
    }

    public void setLifeTime(int time) {
        this.lifeTime = time;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if (this.tickCount > lifeTime) {
                this.remove(RemovalReason.KILLED);
            }
        }
    }

    @Override
    public String getEntityName() {
        return entityName;
    }

    @Override
    public float getEntityScale() {
        return scale;
    }
}
