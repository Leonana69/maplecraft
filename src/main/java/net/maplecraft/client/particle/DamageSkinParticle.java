package net.maplecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
public class DamageSkinParticle extends TextureSheetParticle {
    @SubscribeEvent
    public static void onEntityDamaged(LivingDamageEvent event) {
        assert event != null;
        if (event.getSource().getEntity() instanceof Player player) {
            spawnDamageParticles(Mth.ceil(event.getAmount()), event.getEntity());
        }
    }

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

    public static void spawnDamageParticles(int damage, LivingEntity entity) {
        assert Minecraft.getInstance().player != null;
        Vec3 view = Minecraft.getInstance().player.getViewVector(1).scale(0.6);
        int cnt = (int) Math.log10(damage) + 1;

        for (int i = 0; i < cnt; i++) {
            int digit = damage % 10;
            damage /= 10;
            if (entity.level instanceof ServerLevel _level) {
                _level.sendParticles(BasicDamageSkinParticle.P.get(digit),
                        entity.getX() + (i - cnt / 2.0) * view.z + view.x * (1 + i * 0.1),
                        entity.getY() + entity.getBbHeight() + 1 + (i % 2) * 0.08,
                        entity.getZ() - (i - cnt / 2.0) * view.x + view.z * (1 + i * 0.1),
                        1, 0.001, 0.001, 0.001, 0);
            }
        }
    }
}
