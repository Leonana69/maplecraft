package net.maplecraft.entities;

import net.maplecraft.init.EntitiesInit;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.MapleProjectileEntity;
import net.maplecraft.utils.MapleProjectileItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import java.util.Comparator;
import java.util.List;

public class BombArrowEntity extends MapleProjectileEntity {
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.USE_ARROW_FOR_BOW.get();

    public BombArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.BOMB_ARROW_ENTITY.get(), world);
    }

    public BombArrowEntity(EntityType<? extends BombArrowEntity> type, Level world) {
        super(type, world);
    }

    public BombArrowEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.BOMB_ARROW_ENTITY.get(), entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return canPickUp ? new ItemStack(item) : ItemStack.EMPTY;
    }

    @Override
    public String getProjectileName() {
        return item.itemName;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.isOnGround())
            explodeAt(this, result.getLocation());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().setInvisible(true);
        explodeAt(this, result.getLocation());
        result.getEntity().setInvisible(false);
    }

    public void explodeAt(MapleProjectileEntity entity, Vec3 location) {
        Level world = entity.level;

        world.playSound(null,
                location.x, location.y, location.z,
                SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS,
                4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F);
        if (world instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.EXPLOSION, location.x, location.y, location.z, 15, 1, 1, 1, 0.3);
        }

        List<LivingEntity> list = world.getEntitiesOfClass(LivingEntity.class, new AABB(location, location).inflate(3), e -> true).stream()
                .sorted(Comparator.comparingDouble(e -> e.distanceToSqr(location))).toList();
        Entity source = entity.getOwner() == null ? entity : entity.getOwner();

        for (LivingEntity livingEntity : list) {
            livingEntity.knockback(0.2D, 0.1D, 0.1D);
            livingEntity.hurt(DamageSource.arrow(entity, source), (float) entity.getBaseDamage() * entity.power);
        }
    }
}
