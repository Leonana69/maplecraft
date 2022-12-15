package net.maplecraft.utils;

import net.maplecraft.entities.boss.zakum.BossZakumBodyEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class MapleProjectileEntity extends AbstractArrow {
    public LivingEntity target = null;
    public int skillID = 0;
    public float power = 0;
    public float accuracy = 0;
    private static final EntityDataAccessor<Boolean> ROTATE = SynchedEntityData.defineId(MapleProjectileEntity.class, EntityDataSerializers.BOOLEAN);

    public MapleProjectileEntity(EntityType<? extends MapleProjectileEntity> type, Level world) {
        super(type, world);
    }

    public MapleProjectileEntity(EntityType<? extends MapleProjectileEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    public boolean canPickUp() {
       return !this.getTags().contains("canNotPickUp");
    }

    public void setCanNotPickUp() {
        this.addTag("canNotPickUp");
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ROTATE, false);
    }

    public boolean isRotate() {
        return this.entityData.get(ROTATE);
    }

    public void setRotate(boolean rotate) {
        this.entityData.set(ROTATE, rotate);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("rotate", isRotate());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setRotate(tag.getBoolean("rotate"));
    }

    @Override // generate particle effect while flying
    public void tick() {
        super.tick();
        if (this.level instanceof ServerLevel _level && !this.inGround) {
            _level.sendParticles(ParticleTypes.CRIT,
                    this.getX(), this.getY(), this.getZ(),
                    1, 0.1, 0.1, 0.1, 0.0);
        }

        if (!this.inGround && target != null && !target.isDeadOrDying()) {
            Vec3 move = new Vec3(this.target.getX() - this.getX(),
                    this.target.getY() + this.target.getBbHeight() / 2 - this.getY(),
                    this.target.getZ() - this.getZ());

            move = move.normalize().scale(power);
            this.setDeltaMovement(move);
        }

        if (this.isRotate() && this.inGround) {
            this.setRotate(false);
        }

        if (this.inGround && (this.getPickupItem() == ItemStack.EMPTY) && this.inGroundTime > 200) {
            this.discard();
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    public String getProjectileName() {
        return "";
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        this.target = null;
        this.getLevel().playSound(null, result.getEntity().getX(), result.getEntity().getY(), result.getEntity().getZ(),
                Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_mob_damage"))),
                SoundSource.PLAYERS, 1, 1);
        result.getEntity().invulnerableTime = 0;

        if (AllSkillList.SKILLS.get(skillID) != null
                && this.getOwner() instanceof Player player
                && result.getEntity() instanceof LivingEntity livingEntity) {
            SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(skillID).asItem();
            skill.onHitEffect(player, livingEntity);
        }
    }

    protected boolean isValidTarget(Entity entity) {
        return !this.level.isClientSide
                && entity instanceof LivingEntity
                && this.getOwner() != null
                && this.getOwner() != entity;
    }
}
