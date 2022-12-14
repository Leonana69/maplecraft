package net.maplecraft.entities.boss.zakum;

import com.mojang.math.Vector3f;
import net.maplecraft.utils.MapleProjectileEntity;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BossZakumHandEntity extends Monster implements IAnimatable {
    private static final EntityDataAccessor<Integer> HAND_INDEX = SynchedEntityData.defineId(BossZakumHandEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> BODY_ID = SynchedEntityData.defineId(BossZakumHandEntity.class, EntityDataSerializers.STRING);
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private boolean isDead = false;

    public BossZakumHandEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0F)
                .add(Attributes.ATTACK_SPEED, 1.0F)
                .add(Attributes.FOLLOW_RANGE, 20.0F)
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
    public boolean fireImmune() {
        return true;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAND_INDEX, 0);
        this.entityData.define(BODY_ID, "");
    }

    public void setHandIndex(int index) {
        this.entityData.set(HAND_INDEX, index);
    }

    public int getHandIndex() {
        return this.entityData.get(HAND_INDEX);
    }

    public void setBodyId(LivingEntity livingEntity) {
        this.entityData.set(BODY_ID, livingEntity.getStringUUID());
    }

    public String getBodyId() {
        return this.entityData.get(BODY_ID);
    }

    public BossZakumBodyEntity getBodyEntity() {
        if (this.level instanceof ServerLevel level) {
            UUID uuid = UUID.fromString(this.entityData.get(BODY_ID));
            return (BossZakumBodyEntity) level.getEntity(uuid);
        }
        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("hand_index", getHandIndex());
        tag.putString("body_id", getBodyId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setHandIndex(tag.getInt("hand_index"));
        this.entityData.set(BODY_ID, tag.getString("body_id"));
    }

    @Override
    protected void tickDeath() {
        super.tickDeath();
        if (!this.level.isClientSide && !isDead) {
            BossZakumBodyEntity entity = getBodyEntity();
            if (entity != null) {
                entity.setHandCount(entity.getHandCount() - 1);
            }
            isDead = true;
        }
    }

    public int getAttackDuration() {
        return 80;
    }

    static class ZakumHandAttackGoal extends Goal {
        private final Vec3 attackRange = new Vec3(3, 9, 3);
        private final BossZakumHandEntity handEntity;
        private int attackTime;
        private boolean isAttacking;
        private Vec3 attackLocation;
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
            this.isAttacking = this.handEntity.random.nextFloat() < 0.4;
        }

        @Override
        public void stop() {
            this.handEntity.setTarget(null);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        private void spawnAttackEffect(Vec3 location) {
            if (attackTime == this.handEntity.getAttackDuration() / 2) {
                attackLocation = location;
            }

            if (attackTime >= this.handEntity.getAttackDuration() / 2 && handEntity.level instanceof ServerLevel level) {
                level.sendParticles(new DustParticleOptions(
                                new Vector3f(0.6F, 0.1F, 0.1F), 0.7F),
                        attackLocation.x, attackLocation.y, attackLocation.z,
                        20,
                        attackRange.x / 2, attackRange.y / 2, attackRange.z / 2,
                        attackTime * 0.01);

                if (attackTime < this.handEntity.getAttackDuration() - 20)
                    level.sendParticles(ParticleTypes.DRIPPING_LAVA,
                            attackLocation.x, attackLocation.y, attackLocation.z,
                            10,
                            attackRange.x / 4, attackRange.y / 4, attackRange.z / 4,
                            0);
            }
        }

        private void spawnFinalAttackEffect() {
            if (handEntity.level instanceof ServerLevel level) {
                level.sendParticles(ParticleTypes.SMOKE,
                        attackLocation.x, attackLocation.y, attackLocation.z,
                        1000,
                        0.5, 0.5, 0.5,
                        0.1);
            }
        }

        public List<Player> findPlayerAtPosition() {
            AABB box = new AABB(
                    attackLocation.x - attackRange.x,
                    attackLocation.y - 1,
                    attackLocation.z - attackRange.z,
                    attackLocation.x + attackRange.x,
                    attackLocation.y + attackRange.y,
                    attackLocation.z + attackRange.z);
            return this.handEntity.level.getEntitiesOfClass(Player.class, box);
        }

        public void tick() {
            LivingEntity livingentity = this.handEntity.getTarget();
            if (livingentity != null) {
                if (!this.handEntity.hasLineOfSight(livingentity)) {
                    this.handEntity.setTarget(null);
                } else {
                    ++this.attackTime;
                    if (this.attackTime == 0) {
                    } else if (isAttacking) {
                        spawnAttackEffect(livingentity.position());
                        if (this.attackTime >= this.handEntity.getAttackDuration()) {
                            spawnFinalAttackEffect();
                            float f = 1.0F;
                            if (this.handEntity.level.getDifficulty() == Difficulty.HARD) {
                                f += 2.0F;
                            }

                            List<Player> target = findPlayerAtPosition();
                            for (Player player : target) {
                                player.hurt(DamageSource.indirectMagic(this.handEntity, this.handEntity), f);
                                player.hurt(DamageSource.mobAttack(this.handEntity), (float)this.handEntity.getAttributeValue(Attributes.ATTACK_DAMAGE));
                            }
                        }
                    }

                    if (this.attackTime >= this.handEntity.getAttackDuration())
                        this.handEntity.setTarget(null);

                    super.tick();
                }
            }
        }
    }
}
