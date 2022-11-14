package net.maplecraft.utils;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MapleProjectileEntity extends AbstractArrow {
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
            _level.sendParticles(ParticleTypes.SMOKE,
                    this.getX(), this.getY(), this.getZ(),
                    6, 0.5, 0.5, 0.5, 0.2);
        }
    }
}
