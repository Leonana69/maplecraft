package com.maplecraft.init;

import com.maplecraft.client.particle.BasicDamageSkinParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticlesInit {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_0.get(), BasicDamageSkinParticle.Number0::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_1.get(), BasicDamageSkinParticle.Number1::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_2.get(), BasicDamageSkinParticle.Number2::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_3.get(), BasicDamageSkinParticle.Number3::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_4.get(), BasicDamageSkinParticle.Number4::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_5.get(), BasicDamageSkinParticle.Number5::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_6.get(), BasicDamageSkinParticle.Number6::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_7.get(), BasicDamageSkinParticle.Number7::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_8.get(), BasicDamageSkinParticle.Number8::provider);
        event.register(ParticlesTypeInit.BASIC_DAMAGE_SKIN_9.get(), BasicDamageSkinParticle.Number9::provider);
    }
}

