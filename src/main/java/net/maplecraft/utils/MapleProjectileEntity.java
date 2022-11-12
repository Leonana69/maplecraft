package net.maplecraft.utils;

import net.maplecraft.client.particle.BasicDamageSkinParticle;
import net.maplecraft.client.particle.DamageSkinParticle;
import net.maplecraft.entities.SteelyThrowingKnivesEntity;
import net.maplecraft.init.EntitiesInit;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.init.ParticlesTypeInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

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

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        if (result.getEntity() instanceof LivingEntity entity) {
            float f = (float)this.getDeltaMovement().length();
            int damage = Mth.ceil(Mth.clamp((double)f * this.getBaseDamage(), 0.0D, 2.147483647E9D));
            if (this.isCritArrow()) {
                long j = (long)this.random.nextInt(damage / 2 + 2);
                damage = (int)Math.min(j + (long)damage, 2147483647L);
            }

            if (this.level instanceof ServerLevel _level) {
                DamageSkinParticle.spawnDamageParticles(damage, entity, _level);
            }
        }
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
