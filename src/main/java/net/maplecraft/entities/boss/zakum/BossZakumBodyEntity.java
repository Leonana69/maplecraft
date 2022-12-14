package net.maplecraft.entities.boss.zakum;

import com.mojang.math.Vector3f;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.init.EntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
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

import java.util.List;

public class BossZakumBodyEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Integer> HAND_COUNT = SynchedEntityData.defineId(BossZakumBodyEntity.class, EntityDataSerializers.INT);

    public BossZakumBodyEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 500.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0F)
                .add(Attributes.ATTACK_SPEED, 1.0F)
                .add(Attributes.FOLLOW_RANGE, 20F)
                .add(Attributes.ARMOR, 10.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.0F);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new BossZakumBodyEntity.ZakumBodyAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.boss_zakum_body_entity.idle"));
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

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.setInvulnerable(isInvulnerable());
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAND_COUNT, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("hand_count", getHandCount());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setHandCount(tag.getInt("hand_count"));
    }

    @Override
    protected void tickDeath() {
        super.tickDeath();
    }

    public boolean isInvulnerable() {
        return getHandCount() > 0;
    }

    public void setHandCount(int count) {
        this.entityData.set(HAND_COUNT, count);
    }

    public int getHandCount() {
        return this.entityData.get(HAND_COUNT);
    }

    public int getAttackDuration() {
        return 60;
    }

    static class ZakumBodyAttackGoal extends Goal {
        private final Vec3 attackRange = new Vec3(3, 9, 3);
        private final BossZakumBodyEntity bodyEntity;
        private int attackTime;
        private boolean isAttacking;
        private Vec3 attackLocation;
        public ZakumBodyAttackGoal(BossZakumBodyEntity entity) {
            this.bodyEntity = entity;
        }

        @Override
        public boolean canUse() {
            LivingEntity livingEntity = this.bodyEntity.getTarget();
            return livingEntity != null && livingEntity.isAlive();
        }

        @Override
        public void start() {
            this.attackTime = -10;
            this.isAttacking = !this.bodyEntity.isInvulnerable();
        }

        @Override
        public void stop() {
            this.bodyEntity.setTarget(null);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        private void spawnAttackEffect(Vec3 location) {
            if (attackTime == this.bodyEntity.getAttackDuration() / 2) {
                attackLocation = location;
            }

            if (attackTime >= this.bodyEntity.getAttackDuration() / 2 && bodyEntity.level instanceof ServerLevel level) {
                level.sendParticles(new DustParticleOptions(
                                new Vector3f(0.8F, 0.4F, 0.4F), 1.4F),
                        attackLocation.x, attackLocation.y, attackLocation.z,
                        20,
                        attackRange.x / 2, attackRange.y / 2, attackRange.z / 2,
                        attackTime * 0.01);

                if (attackTime < this.bodyEntity.getAttackDuration() - 20)
                    level.sendParticles(ParticleTypes.FLAME,
                            attackLocation.x, attackLocation.y, attackLocation.z,
                            10,
                            attackRange.x / 4, attackRange.y / 4, attackRange.z / 4,
                            0);
            }
        }

        private void spawnFinalAttackEffect() {
            if (bodyEntity.level instanceof ServerLevel level) {
                level.sendParticles(ParticleTypes.ASH,
                        attackLocation.x, attackLocation.y, attackLocation.z,
                        1000,
                        0.5, 0.5, 0.5,
                        0.05);

                if (this.bodyEntity.random.nextFloat() < 0.4) {
                    LightningBolt entity = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                    entity.setDamage((float)this.bodyEntity.getAttributeValue(Attributes.ATTACK_DAMAGE));
                    entity.setPos(attackLocation);
                    level.addFreshEntity(entity);
                }
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
            return this.bodyEntity.level.getEntitiesOfClass(Player.class, box);
        }

        public void tick() {
            LivingEntity livingentity = this.bodyEntity.getTarget();
            if (livingentity != null) {
                if (!this.bodyEntity.hasLineOfSight(livingentity)) {
                    this.bodyEntity.setTarget(null);
                } else {
                    ++this.attackTime;
                    if (this.attackTime == 0) {
                    } else if (isAttacking) {
                        spawnAttackEffect(livingentity.position());
                        if (this.attackTime >= this.bodyEntity.getAttackDuration()) {
                            spawnFinalAttackEffect();
                            float f = 1.0F;
                            if (this.bodyEntity.level.getDifficulty() == Difficulty.HARD) {
                                f += 2.0F;
                            }

                            List<Player> target = findPlayerAtPosition();
                            for (Player player : target) {
                                player.hurt(DamageSource.indirectMagic(this.bodyEntity, this.bodyEntity), f);
                                player.hurt(DamageSource.mobAttack(this.bodyEntity), (float)this.bodyEntity.getAttributeValue(Attributes.ATTACK_DAMAGE));
                            }
                        }
                    }

                    if (this.attackTime >= this.bodyEntity.getAttackDuration())
                        this.bodyEntity.setTarget(null);

                    super.tick();
                }
            }
        }
    }
}
