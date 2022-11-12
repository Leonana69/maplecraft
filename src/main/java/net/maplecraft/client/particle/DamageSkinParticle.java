package net.maplecraft.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;

@OnlyIn(Dist.CLIENT)
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

    private final SpriteSet spriteSet;

    protected DamageSkinParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.2f, 0.2f);
        this.quadSize *= 3f;
        this.lifetime = (int) Math.max(1, 30 + (this.random.nextInt(10) - 5));
        this.gravity = -0.1f;
        this.hasPhysics = false;
        this.xd = vx * 0.1;
        this.yd = vy * 0.1;
        this.zd = vz * 0.1;
        this.pickSprite(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.alpha /= 1.05;
    }

    public static void spawnDamageParticles(int damage, Entity entity, ServerLevel world) {
        do {
            int digit = damage % 10;
            damage /= 10;
            world.sendParticles(BasicDamageSkinParticle.P.get(digit), (entity.getX()), (entity.getY() + 2),
                    (entity.getZ()), 1, 0.1, 0.1, 0.1, 0.05);
        } while (damage > 10);
    }
}
