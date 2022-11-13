package net.maplecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

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

    protected DamageSkinParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
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
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.alpha /= 1.02;
    }

    public static void spawnDamageParticles(int damage, LivingEntity entity) {
        assert Minecraft.getInstance().player != null;
        Vec3 view = Minecraft.getInstance().player.getViewVector(1).scale(0.6);
        int cnt = (int) Math.log10(damage) + 1;
        for (int i = 0; i < cnt; i++) {
            int digit = damage % 10;
            damage /= 10;
            if (entity.level instanceof ServerLevel _level) {
                _level.sendParticles(BasicDamageSkinParticle.P.get(digit),
                        entity.getX() + (i - cnt / 2.0) * view.z + view.x,
                        entity.getY() + 2,
                        entity.getZ() - (i - cnt / 2.0) * view.x + view.z,
                        1, 0.01, 0.01, 0.01, 0.05);
            }
        }
    }
}
