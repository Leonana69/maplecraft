package com.maplecraft.init;

import com.maplecraft.MapleCraftMod;
import com.maplecraft.client.particle.BasicDamageSkinParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticlesTypeInit {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.register(BASIC_DAMAGE_SKIN_0.get(), BasicDamageSkinParticle.Number0::provider);
        event.register(BASIC_DAMAGE_SKIN_1.get(), BasicDamageSkinParticle.Number1::provider);
        event.register(BASIC_DAMAGE_SKIN_2.get(), BasicDamageSkinParticle.Number2::provider);
        event.register(BASIC_DAMAGE_SKIN_3.get(), BasicDamageSkinParticle.Number3::provider);
        event.register(BASIC_DAMAGE_SKIN_4.get(), BasicDamageSkinParticle.Number4::provider);
        event.register(BASIC_DAMAGE_SKIN_5.get(), BasicDamageSkinParticle.Number5::provider);
        event.register(BASIC_DAMAGE_SKIN_6.get(), BasicDamageSkinParticle.Number6::provider);
        event.register(BASIC_DAMAGE_SKIN_7.get(), BasicDamageSkinParticle.Number7::provider);
        event.register(BASIC_DAMAGE_SKIN_8.get(), BasicDamageSkinParticle.Number8::provider);
        event.register(BASIC_DAMAGE_SKIN_9.get(), BasicDamageSkinParticle.Number9::provider);
    }

    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MapleCraftMod.MODID);
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_0 = registerParticle("basic_damage_skin_0");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_1 = registerParticle("basic_damage_skin_1");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_2 = registerParticle("basic_damage_skin_2");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_3 = registerParticle("basic_damage_skin_3");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_4 = registerParticle("basic_damage_skin_4");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_5 = registerParticle("basic_damage_skin_5");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_6 = registerParticle("basic_damage_skin_6");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_7 = registerParticle("basic_damage_skin_7");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_8 = registerParticle("basic_damage_skin_8");
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_9 = registerParticle("basic_damage_skin_9");

    static RegistryObject<SimpleParticleType> registerParticle(String name) {
        return REGISTRY.register(name, () -> new SimpleParticleType(false));
    }
}
