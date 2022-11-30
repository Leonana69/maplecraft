package net.maplecraft.entities.boss.zakum;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BossZakumHandEntity extends Monster implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public BossZakumHandEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0F)
                .add(Attributes.ATTACK_SPEED, 1.0F)
                .add(Attributes.ARMOR, 10.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.0F);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.boss_zakum_hand_entity.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
