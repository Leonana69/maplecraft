package net.maplecraft.entities.boss.zakum;

import net.maplecraft.utils.MapleProjectileEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class BossZakumHandEntity extends Monster implements IAnimatable {
    private LivingEntity zakumBodyEntity;
    private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(BossZakumHandEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HAND_INDEX = SynchedEntityData.defineId(BossZakumHandEntity.class, EntityDataSerializers.INT);
    @Nullable
    private LivingEntity clientSideCachedAttackTarget;
    private int clientSideAttackTime;
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public BossZakumHandEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0F)
                .add(Attributes.ATTACK_SPEED, 1.0F)
                .add(Attributes.FOLLOW_RANGE, 15.0F)
                .add(Attributes.ARMOR, 10.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ZakumHandAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
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

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_ATTACK_TARGET, 0);
        this.entityData.define(HAND_INDEX, 0);
    }

    public void setZakumBodyEntity(LivingEntity body) {
        zakumBodyEntity = body;
    }

    public void setHandIndex(int index) {
        this.entityData.set(HAND_INDEX, index);
    }

    public int getHandIndex() {
        return this.entityData.get(HAND_INDEX);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("hand_index", getHandIndex());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setHandIndex(tag.getInt("hand_index"));
    }

    @Override
    protected void tickDeath() {
        super.tickDeath();
        if (!this.level.isClientSide && zakumBodyEntity != null) {
            zakumBodyEntity.getTags().remove("hand_" + getHandIndex());
            zakumBodyEntity = null;
        }
    }

    void setActiveAttackTarget(int entityID) {
        this.entityData.set(DATA_ID_ATTACK_TARGET, entityID);
    }

    public boolean hasActiveAttackTarget() {
        return this.entityData.get(DATA_ID_ATTACK_TARGET) != 0;
    }

    @Override
    public void aiStep() {
        if (this.isAlive() && this.level.isClientSide) {
            if (this.hasActiveAttackTarget()) {
                if (this.clientSideAttackTime < this.getAttackDuration()) {
                    ++this.clientSideAttackTime;
                }
            }
        }
        super.aiStep();
    }

    @Nullable
    public LivingEntity getActiveAttackTarget() {
        if (!this.hasActiveAttackTarget()) {
            return null;
        } else if (this.level.isClientSide) {
            if (this.clientSideCachedAttackTarget != null) {
                return this.clientSideCachedAttackTarget;
            } else {
                Entity entity = this.level.getEntity(this.entityData.get(DATA_ID_ATTACK_TARGET));
                if (entity instanceof LivingEntity) {
                    this.clientSideCachedAttackTarget = (LivingEntity)entity;
                    return this.clientSideCachedAttackTarget;
                } else {
                    return null;
                }
            }
        } else {
            return this.getTarget();
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> entityData) {
        super.onSyncedDataUpdated(entityData);
        if (DATA_ID_ATTACK_TARGET.equals(entityData)) {
            this.clientSideAttackTime = 0;
            this.clientSideCachedAttackTarget = null;
        }
    }

    public int getAttackDuration() {
        return 80;
    }

    static class ZakumHandAttackGoal extends Goal {
        private final BossZakumHandEntity handEntity;
        private int attackTime;
        public ZakumHandAttackGoal(BossZakumHandEntity entity) {
            this.handEntity = entity;
        }

        @Override
        public boolean canUse() {
            LivingEntity livingEntity = this.handEntity.getTarget();
            return livingEntity != null && livingEntity.isAlive();
        }

        @Override
        public void start() {
            this.attackTime = -10;
        }

        @Override
        public void stop() {
            this.handEntity.setActiveAttackTarget(0);
            this.handEntity.setTarget(null);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.handEntity.getTarget();
            if (livingentity != null) {
                if (!this.handEntity.hasLineOfSight(livingentity)) {
                    this.handEntity.setTarget(null);
                } else {
                    ++this.attackTime;
                    if (this.attackTime == 0) {
                        this.handEntity.setActiveAttackTarget(livingentity.getId());
                    } else if (this.attackTime >= this.handEntity.getAttackDuration()) {
                        float f = 1.0F;
                        if (this.handEntity.level.getDifficulty() == Difficulty.HARD) {
                            f += 2.0F;
                        }

                        livingentity.hurt(DamageSource.indirectMagic(this.handEntity, this.handEntity), f);
                        livingentity.hurt(DamageSource.mobAttack(this.handEntity), (float)this.handEntity.getAttributeValue(Attributes.ATTACK_DAMAGE));
                        this.handEntity.setTarget(null);
                    }

                    super.tick();
                }
            }
        }
    }
}
