package com.maplecraft.init;

import com.maplecraft.MapleCraftMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticlesTypeInit {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MapleCraftMod.MODID);
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_0 = REGISTRY.register("basic_damage_skin_0",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_1 = REGISTRY.register("basic_damage_skin_1",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_2 = REGISTRY.register("basic_damage_skin_2",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_3 = REGISTRY.register("basic_damage_skin_3",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_4 = REGISTRY.register("basic_damage_skin_4",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_5 = REGISTRY.register("basic_damage_skin_5",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_6 = REGISTRY.register("basic_damage_skin_6",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_7 = REGISTRY.register("basic_damage_skin_7",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_8 = REGISTRY.register("basic_damage_skin_8",
            () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BASIC_DAMAGE_SKIN_9 = REGISTRY.register("basic_damage_skin_9",
            () -> new SimpleParticleType(false));
}
