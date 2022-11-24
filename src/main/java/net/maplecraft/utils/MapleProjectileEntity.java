package net.maplecraft.utils;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MapleProjectileEntity extends AbstractArrow {
    public LivingEntity target = null;
    public float damage = 0;

    public MapleProjectileEntity(EntityType<? extends MapleProjectileEntity> type, Level world) {
        super(type, world);
    }

    public MapleProjectileEntity(EntityType<? extends MapleProjectileEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override // generate particle effect while flying
    public void tick() {
        super.tick();
        if (this.level instanceof ServerLevel _level && !this.inGround) {
            _level.sendParticles(ParticleTypes.CRIT,
                    this.getX(), this.getY(), this.getZ(),
                    1, 0.1, 0.1, 0.1, 0.0);
        }

        if (target != null) {
            System.out.println("has target");
            Vec3 move = new Vec3(this.target.getX() - this.getX(),
                    this.target.getY() - this.getY(),
                    this.target.getZ() - this.getZ());

            move = move.normalize().scale(WeaponClawItem.power);
            System.out.println("SET: " + move.length());
            this.setDeltaMovement(move);
        }

        System.out.println("length: " + this.getDeltaMovement().length());
    }
}
