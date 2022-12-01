package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CustomEffectsInit {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MapleCraftMod.MODID);
    public static final RegistryObject<MobEffect> EQUIP_SPEED_BOOST = REGISTRY.register("equip_speed_boost", EquipSpeedPercentBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_HEALTH_BOOST = REGISTRY.register("equip_health_boost", EquipHealthBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_ATTACK_BOOST = REGISTRY.register("equip_attack_boost", EquipAttackBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_ATTACK_PERCENT_BOOST = REGISTRY.register("equip_attack_percent_boost", EquipAttackPercentBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_ATTACK_SPEED_BOOST = REGISTRY.register("equip_attack_speed_boost", EquipAttackSpeedBoostMobEffect::new);
    public static final RegistryObject<MobEffect> BUFF_ATTACK_SPEED_BOOST = REGISTRY.register("buff_speed_boost", BuffSpeedBoostMobEffect::new);
}
