package net.maplecraft.client.particle;

import net.maplecraft.init.ParticlesTypeInit;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.multiplayer.ClientLevel;

import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class BasicDamageSkinParticle {
    public final static List<SimpleParticleType> P = Arrays.asList(
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_0.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_1.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_2.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_3.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_4.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_5.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_6.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_7.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_8.get(),
            ParticlesTypeInit.BASIC_DAMAGE_SKIN_9.get()
    );

    public class BasicDamageSkin0Particle extends DamageSkinParticle {
        protected BasicDamageSkin0Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin1Particle extends DamageSkinParticle {
        protected BasicDamageSkin1Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin2Particle extends DamageSkinParticle {
        protected BasicDamageSkin2Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin3Particle extends DamageSkinParticle {
        protected BasicDamageSkin3Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin4Particle extends DamageSkinParticle {
        protected BasicDamageSkin4Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin5Particle extends DamageSkinParticle {
        protected BasicDamageSkin5Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin6Particle extends DamageSkinParticle {
        protected BasicDamageSkin6Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin7Particle extends DamageSkinParticle {
        protected BasicDamageSkin7Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin8Particle extends DamageSkinParticle {
        protected BasicDamageSkin8Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }

    public class BasicDamageSkin9Particle extends DamageSkinParticle {
        protected BasicDamageSkin9Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
            super(world, x, y, z, vx, vy, vz, spriteSet);
        }
    }
}