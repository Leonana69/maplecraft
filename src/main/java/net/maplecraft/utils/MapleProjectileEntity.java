package net.maplecraft.utils;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.lang.reflect.MalformedParameterizedTypeException;

public class MapleProjectileEntity extends AbstractArrow {
    public LivingEntity target = null;
    public int skillID = 0;
    public float damage = 0;
    public float power = 0;
    public float accuracy = 0;

    public MapleProjectileEntity(EntityType<? extends MapleProjectileEntity> type, Level world) {
        super(type, world);
    }

    public MapleProjectileEntity(EntityType<? extends MapleProjectileEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
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
}
