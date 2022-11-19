package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.effect.EquipAttackBoostMobEffect;
import net.maplecraft.effect.EquipHealthBoostMobEffect;
import net.maplecraft.effect.EquipSpeedBoostMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EquipEffectsInit {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MapleCraftMod.MODID);
    public static final RegistryObject<MobEffect> EQUIP_SPEED_BOOST = REGISTRY.register("equip_speed_boost", EquipSpeedBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_HEALTH_BOOST = REGISTRY.register("equip_health_boost", EquipHealthBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_ATTACK_BOOST = REGISTRY.register("equip_attack_boost", EquipAttackBoostMobEffect::new);
}
