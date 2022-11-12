package dev.maplecraft.entities;

import dev.maplecraft.init.EntitiesInit;
import dev.maplecraft.init.ItemsInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

public class SubiThrowingStarsEntity extends AbstractArrow {
    public SubiThrowingStarsEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.SUBI_THROWING_STARS_ENTITY.get(), world);
    }
    public SubiThrowingStarsEntity(EntityType<? extends SubiThrowingStarsEntity> type, Level world) {
        super(type, world);
    }

    public SubiThrowingStarsEntity(LivingEntity entity, Level world) {
        super(EntitiesInit.SUBI_THROWING_STARS_ENTITY.get(), entity, world);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        // this will overwrite shooter's damage
//        result.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), (float) 7);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState theBlockYouHit = this.level.getBlockState(result.getBlockPos());
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override // generate particle effect while flying
    public void tick() {
        super.tick();
        if (this.level instanceof ServerLevel _level && !this.inGround) {
            _level.sendParticles(ParticleTypes.SMOKE,
                this.getX(), this.getY(), this.getZ(),
                6, 0.5, 0.5, 0.5, 0.2);
        }
    }
}
