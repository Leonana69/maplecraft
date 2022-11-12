package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsInit {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MapleCraftMod.MODID);
    // creative tab icon
    public static final RegistryObject<Item> MAPLE_STORY_ICON = REGISTRY.register("maple_story_icon", () -> new Item(new Item.Properties().tab(null)));

    // consumable items
    public static final RegistryObject<Item> USE_RED_POTION = REGISTRY.register("use_red_potion", RedPotionUseItem::new);
    public static final RegistryObject<Item> USE_ELIXIR = REGISTRY.register("use_elixir", ElixirUseItem::new);
    public static final RegistryObject<Item> USE_POWER_ELIXIR = REGISTRY.register("use_power_elixir", PowerElixirUseItem::new);
    public static final RegistryObject<Item> UES_SUBI_THROWING_STARS = REGISTRY.register("use_subi_throwing_stars", SubiThrowingStarsUseItem::new);
    public static final RegistryObject<Item> USE_STEELY_THROWING_KNIVES = REGISTRY.register("use_steely_throwing_knives", SteelyThrowingKnivesUseItem::new);
    // weapons
    public static final RegistryObject<Item> WEAPON_GARNIER_CLAW = REGISTRY.register("weapon_garnier_claw", GarnierClawWeaponItem::new);
    public static final RegistryObject<Item> WEAPON_BRONZE_IGOR_CLAW = REGISTRY.register("weapon_bronze_igor_claw", BronzeIgorClawWeaponItem::new);

    // etc
    public static final RegistryObject<Item> ETC_MESO_TINY = REGISTRY.register("etc_meso_tiny", MesoTinyEtcItem::new);
    public static final RegistryObject<Item> ETC_MESO_SMALL = REGISTRY.register("etc_meso_small", MesoSmallEtcItem::new);
    public static final RegistryObject<Item> ETC_MESO_MEDIUM = REGISTRY.register("etc_meso_medium", MesoMediumEtcItem::new);
    public static final RegistryObject<Item> ETC_MESO_LARGE = REGISTRY.register("etc_meso_large", MesoLargeEtcItem::new);
}
