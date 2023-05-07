package com.maplecraft.init;

import com.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundsInit {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MapleCraftMod.MODID);

    // weapons attack sound
    public static final RegistryObject<SoundEvent> SOUND_ATTACK_SWORD = registerSoundEvent("sound_attack_sword");
    public static final RegistryObject<SoundEvent> SOUND_ATTACK_SPEAR = registerSoundEvent("sound_attack_spear");
    public static final RegistryObject<SoundEvent> SOUND_ATTACK_CLAW = registerSoundEvent("sound_attack_claw");
    public static final RegistryObject<SoundEvent> SOUND_ATTACK_BOW = registerSoundEvent("sound_attack_bow");
    public static final RegistryObject<SoundEvent> SOUND_ENCHANT_SUCCESS = registerSoundEvent("sound_enchant_success");
    public static final RegistryObject<SoundEvent> SOUND_ENCHANT_FAILED = registerSoundEvent("sound_enchant_failed");

    public static final RegistryObject<SoundEvent> SOUND_MOB_DAMAGE = registerSoundEvent("sound_mob_damage");

    // warrior
    public static final RegistryObject<SoundEvent> SOUND_SKILL_SLASH_BLAST = registerSoundEvent("sound_skill_slash_blast");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_POWER_STRIKE = registerSoundEvent("sound_skill_power_strike");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_RAGE = registerSoundEvent("sound_skill_rage");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_PANIC = registerSoundEvent("sound_skill_panic");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_SHOUT = registerSoundEvent("sound_skill_shout");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_COMBO_ATTACK = registerSoundEvent("sound_skill_combo_attack");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_IRON_WILL = registerSoundEvent("sound_skill_iron_will");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_DRAGON_FURY = registerSoundEvent("sound_skill_dragon_fury");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_SPEAR_CRUSHER = registerSoundEvent("sound_skill_spear_crusher");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_DRAGON_ROAR = registerSoundEvent("sound_skill_dragon_roar");

    // magician
    public static final RegistryObject<SoundEvent> SOUND_SKILL_TELEPORT = registerSoundEvent("sound_skill_teleport");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_MAGIC_CLAW = registerSoundEvent("sound_skill_magic_claw");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_FIRE_ARROW = registerSoundEvent("sound_skill_fire_arrow");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_POISON_BRACE = registerSoundEvent("sound_skill_poison_brace");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_EXPLOSION = registerSoundEvent("sound_skill_explosion");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_ELEMENT_COMPOSITION_FP = registerSoundEvent("sound_skill_element_composition_fp");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_THUNDERBOLT = registerSoundEvent("sound_skill_thunderbolt");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_COLD_BEAM = registerSoundEvent("sound_skill_cold_beam");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_ICE_STRIKE = registerSoundEvent("sound_skill_ice_strike");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_ELEMENT_COMPOSITION_IL = registerSoundEvent("sound_skill_element_composition_il");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_HEAL = registerSoundEvent("sound_skill_heal");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_HOLY_ARROW = registerSoundEvent("sound_skill_holy_arrow");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_SHINING_RAY = registerSoundEvent("sound_skill_shining_ray");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_HOLY_DRAGON = registerSoundEvent("sound_skill_holy_dragon");

    // bowman
    public static final RegistryObject<SoundEvent> SOUND_SKILL_ARROW_BLOW = registerSoundEvent("sound_skill_arrow_blow");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_SOUL_ARROW = registerSoundEvent("sound_skill_soul_arrow");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_ARROW_BOMB = registerSoundEvent("sound_skill_arrow_bomb");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_IRON_ARROW = registerSoundEvent("sound_skill_iron_arrow");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_STRAFE = registerSoundEvent("sound_skill_strafe");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_ARROW_RAIN = registerSoundEvent("sound_skill_arrow_rain");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_ARROW_ERUPTION = registerSoundEvent("sound_skill_arrow_eruption");

    // thief
    public static final RegistryObject<SoundEvent> SOUND_SKILL_LUCKY_SEVEN = registerSoundEvent("sound_skill_lucky_seven");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_DOUBLE_STAB = registerSoundEvent("sound_skill_double_stab");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_HASTE = registerSoundEvent("sound_skill_haste");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_DRAIN = registerSoundEvent("sound_skill_drain");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_SAVAGE_BLOW = registerSoundEvent("sound_skill_savage_blow");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_SHADOW_PARTNER = registerSoundEvent("sound_skill_shadow_partner");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_AVENGER = registerSoundEvent("sound_skill_avenger");

    public static final RegistryObject<SoundEvent> SOUND_SKILL_ASSAULTER = registerSoundEvent("sound_skill_assaulter");
    public static final RegistryObject<SoundEvent> SOUND_SKILL_MESO_EXPLOSION = registerSoundEvent("sound_skill_meso_explosion");

    static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return REGISTRY.register(name, () -> new SoundEvent(new ResourceLocation(MapleCraftMod.MODID, name)));
    }
}
