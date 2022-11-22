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
    public static final RegistryObject<SoundEvent> SOUND_SWORD_ATTACK = REGISTRY.register("sound_sword_attack",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_sword_attack")));
    public static final RegistryObject<SoundEvent> SOUND_SPEAR_ATTACK = REGISTRY.register("sound_spear_attack",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_spear_attack")));
    public static final RegistryObject<SoundEvent> SOUND_CLAW_ATTACK = REGISTRY.register("sound_claw_attack",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_claw_attack")));
    public static final RegistryObject<SoundEvent> SOUND_CLAW_ATTACK_SWING = REGISTRY.register("sound_claw_attack_swing",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_claw_attack_swing")));
    public static final RegistryObject<SoundEvent> SOUND_BOW_ATTACK = REGISTRY.register("sound_bow_attack",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_bow_attack")));
    public static final RegistryObject<SoundEvent> SOUND_BOW_ATTACK_SWING = REGISTRY.register("sound_bow_attack_swing",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_bow_attack_swing")));
    public static final RegistryObject<SoundEvent> SOUND_ENCHANT_SUCCESS = REGISTRY.register("sound_enchant_success",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_enchant_success")));
    public static final RegistryObject<SoundEvent> SOUND_ENCHANT_FAILED = REGISTRY.register("sound_enchant_failed",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_enchant_failed")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_TELEPORT = REGISTRY.register("sound_skill_teleport",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_teleport")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_MAGIC_CLAW = REGISTRY.register("sound_skill_magic_claw",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_magic_claw")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_THUNDER_BOLT = REGISTRY.register("sound_skill_thunder_bolt",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_thunder_bolt")));

    public static final RegistryObject<SoundEvent> SOUND_MOB_DAMAGE = REGISTRY.register("sound_mob_damage",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_mob_damage")));
}
