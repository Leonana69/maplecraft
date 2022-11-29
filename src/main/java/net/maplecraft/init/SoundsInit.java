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
    public static final RegistryObject<SoundEvent> SOUND_ATTACK_SWORD = REGISTRY.register("sound_attack_sword",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_attack_sword")));
    public static final RegistryObject<SoundEvent> SOUND_ATTACK_SPEAR = REGISTRY.register("sound_attack_spear",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_attack_spear")));
    public static final RegistryObject<SoundEvent> SOUND_ATTACK_CLAW = REGISTRY.register("sound_attack_claw",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_attack_claw")));
    public static final RegistryObject<SoundEvent> SOUND_ATTACK_BOW = REGISTRY.register("sound_attack_bow",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_attack_bow")));
    public static final RegistryObject<SoundEvent> SOUND_ENCHANT_SUCCESS = REGISTRY.register("sound_enchant_success",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_enchant_success")));
    public static final RegistryObject<SoundEvent> SOUND_ENCHANT_FAILED = REGISTRY.register("sound_enchant_failed",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_enchant_failed")));

    public static final RegistryObject<SoundEvent> SOUND_MOB_DAMAGE = REGISTRY.register("sound_mob_damage",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_mob_damage")));

    // warrior
    public static final RegistryObject<SoundEvent> SOUND_SKILL_SLASH_BLAST = REGISTRY.register("sound_skill_slash_blast",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_slash_blast")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_POWER_STRIKE = REGISTRY.register("sound_skill_power_strike",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_power_strike")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_DRAGON_FURY = REGISTRY.register("sound_skill_dragon_fury",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_dragon_fury")));

    // magician
    public static final RegistryObject<SoundEvent> SOUND_SKILL_TELEPORT = REGISTRY.register("sound_skill_teleport",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_teleport")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_MAGIC_CLAW = REGISTRY.register("sound_skill_magic_claw",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_magic_claw")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_FIRE_ARROW = REGISTRY.register("sound_skill_fire_arrow",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_fire_arrow")));


    public static final RegistryObject<SoundEvent> SOUND_SKILL_THUNDERBOLT = REGISTRY.register("sound_skill_thunderbolt",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_thunderbolt")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_COLD_BEAM = REGISTRY.register("sound_skill_cold_beam",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_cold_beam")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_HEAL = REGISTRY.register("sound_skill_heal",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_heal")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_HOLY_ARROW = REGISTRY.register("sound_skill_holy_arrow",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_holy_arrow")));

    // bowman
    public static final RegistryObject<SoundEvent> SOUND_SKILL_ARROW_BLOW = REGISTRY.register("sound_skill_arrow_blow",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_arrow_blow")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_ARROW_BOMB = REGISTRY.register("sound_skill_arrow_bomb",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_arrow_bomb")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_IRON_ARROW = REGISTRY.register("sound_skill_iron_arrow",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_iron_arrow")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_STRAFE = REGISTRY.register("sound_skill_strafe",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_strafe")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_ARROW_RAIN = REGISTRY.register("sound_skill_arrow_rain",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_arrow_rain")));

    // thief
    public static final RegistryObject<SoundEvent> SOUND_SKILL_LUCKY_SEVEN = REGISTRY.register("sound_skill_lucky_seven",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_lucky_seven")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_DOUBLE_STAB = REGISTRY.register("sound_skill_double_stab",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_double_stab")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_HASTE = REGISTRY.register("sound_skill_haste",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_haste")));
    public static final RegistryObject<SoundEvent> SOUND_SKILL_DRAIN = REGISTRY.register("sound_skill_drain",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_drain")));

    public static final RegistryObject<SoundEvent> SOUND_SKILL_SAVAGE_BLOW = REGISTRY.register("sound_skill_savage_blow",
            () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, "sound_skill_savage_blow")));

}
