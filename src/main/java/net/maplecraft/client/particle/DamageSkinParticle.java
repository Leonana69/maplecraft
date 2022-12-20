package net.maplecraft.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class DamageSkinParticle extends TextureSheetParticle {
    public static DamageSkinParticleProvider provider(SpriteSet spriteSet) {
        return new DamageSkinParticleProvider(spriteSet);
    }

    public static class DamageSkinParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public DamageSkinParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed,
                                       double zSpeed) {
            return new DamageSkinParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }

    protected DamageSkinParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.setSize(0.2f, 0.2f);
        this.quadSize = 0.4f; // particle size
        this.lifetime = 30;
        this.gravity = -0.1f;
        this.hasPhysics = false;
        this.xd = vx * 0;
        this.yd = vy * 0;
        this.zd = vz * 0;
        this.pickSprite(spriteSet);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.alpha /= 1.03;
    }
}
