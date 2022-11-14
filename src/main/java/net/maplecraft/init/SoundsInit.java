package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundsInit {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MapleCraftMod.MODID);

    // weapons attack sound
    public static final RegistryObject<SoundEvent> SOUND_CLAW_ATTACK = REGISTRY.register("sound_claw_attack",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_claw_attack")));
    public static final RegistryObject<SoundEvent> SOUND_BOW_ATTACK = REGISTRY.register("sound_bow_attack",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_bow_attack")));
}
