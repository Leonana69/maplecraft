package net.maplecraft.init;

import net.maplecraft.client.particle.BasicDamageSkinParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticlesInit {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_0.get(), BasicDamageSkinParticle.BasicDamageSkin0Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_1.get(), BasicDamageSkinParticle.BasicDamageSkin1Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_2.get(), BasicDamageSkinParticle.BasicDamageSkin2Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_3.get(), BasicDamageSkinParticle.BasicDamageSkin3Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_4.get(), BasicDamageSkinParticle.BasicDamageSkin4Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_5.get(), BasicDamageSkinParticle.BasicDamageSkin5Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_6.get(), BasicDamageSkinParticle.BasicDamageSkin6Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_7.get(), BasicDamageSkinParticle.BasicDamageSkin7Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_8.get(), BasicDamageSkinParticle.BasicDamageSkin8Particle::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_9.get(), BasicDamageSkinParticle.BasicDamageSkin9Particle::provider);
    }
}

