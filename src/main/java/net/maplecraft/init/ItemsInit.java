package net.maplecraft.init;

import io.netty.util.Attribute;
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
    public static final RegistryObject<Item> USE_BLUE_POTION = REGISTRY.register("use_blue_potion", BluePotionUseItem::new);
    public static final RegistryObject<Item> USE_ORANGE_POTION = REGISTRY.register("use_orange_potion", OrangePotionUseItem::new);
    public static final RegistryObject<Item> USE_WHITE_POTION = REGISTRY.register("use_white_potion", WhitePotionUseItem::new);
    public static final RegistryObject<Item> USE_ELIXIR = REGISTRY.register("use_elixir", ElixirUseItem::new);
    public static final RegistryObject<Item> USE_POWER_ELIXIR = REGISTRY.register("use_power_elixir", PowerElixirUseItem::new);
    public static final RegistryObject<Item> USE_ALL_CURE_POTION = REGISTRY.register("use_all_cure_potion", AllCurePotionUseItem::new);
    // shuriken
    public static final RegistryObject<Item> UES_SUBI_THROWING_STARS = REGISTRY.register("use_subi_throwing_stars", SubiThrowingStarsUseItem::new);
    public static final RegistryObject<Item> USE_STEELY_THROWING_KNIVES = REGISTRY.register("use_steely_throwing_knives", SteelyThrowingKnivesUseItem::new);
    public static final RegistryObject<Item> USE_BALANCED_FURY = REGISTRY.register("use_balanced_fury", BalancedFuryUseItem::new);
    // weapons
    public static final RegistryObject<Item> WEAPON_GARNIER_CLAW = REGISTRY.register("weapon_garnier_claw", GarnierClawWeaponItem::new);
    public static final RegistryObject<Item> WEAPON_BRONZE_IGOR_CLAW = REGISTRY.register("weapon_bronze_igor_claw", BronzeIgorClawWeaponItem::new);

    public static final RegistryObject<Item> WEAPON_HUNTERS_BOW = REGISTRY.register("weapon_hunters_bow", HuntersBowWeaponItem::new);
    public static final RegistryObject<Item> WEAPON_MAPLE_BOW = REGISTRY.register("weapon_maple_bow", MapleBowWeaponItem::new);

    // etc
    public static final RegistryObject<Item> ETC_MESO_TINY = REGISTRY.register("etc_meso_tiny", MesoTinyEtcItem::new);
    public static final RegistryObject<Item> ETC_MESO_SMALL = REGISTRY.register("etc_meso_small", MesoSmallEtcItem::new);
    public static final RegistryObject<Item> ETC_MESO_MEDIUM = REGISTRY.register("etc_meso_medium", MesoMediumEtcItem::new);
    public static final RegistryObject<Item> ETC_MESO_LARGE = REGISTRY.register("etc_meso_large", MesoLargeEtcItem::new);
}
