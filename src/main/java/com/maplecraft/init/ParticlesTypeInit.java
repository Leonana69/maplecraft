package com.maplecraft.init;

import com.maplecraft.MapleCraftMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticlesTypeInit {
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
