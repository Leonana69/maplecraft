package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.effect.EquipSpeedBoostMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MobEffectsInit {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MapleCraftMod.MODID);
    public static final RegistryObject<MobEffect> EQUIP_SPEED_BOOST = REGISTRY.register("equip_speed_boost", () -> new EquipSpeedBoostMobEffect());
}
