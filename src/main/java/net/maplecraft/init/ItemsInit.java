package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.item.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.common.subtyping.qual.Bottom;

public class ItemsInit {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MapleCraftMod.MODID);
    /* creative tab icon */
    public static final RegistryObject<Item> MAPLE_STORY_ICON = REGISTRY.register("maple_story_icon", () -> new Item(new Item.Properties().tab(null)));

    /* consumable item */
    // hp
    public static final RegistryObject<Item> USE_RED_POTION = REGISTRY.register("use_red_potion", UseRedPotionItem::new);
    public static final RegistryObject<Item> USE_BLUE_POTION = REGISTRY.register("use_blue_potion", UseBluePotionItem::new);
    public static final RegistryObject<Item> USE_ORANGE_POTION = REGISTRY.register("use_orange_potion", UseOrangePotionItem::new);
    public static final RegistryObject<Item> USE_WHITE_POTION = REGISTRY.register("use_white_potion", UseWhitePotionItem::new);
    // mana
    public static final RegistryObject<Item> USE_MANA_ELIXIR = REGISTRY.register("use_mana_elixir", UseManaElixirItem::new);
    // both
    public static final RegistryObject<Item> USE_ELIXIR = REGISTRY.register("use_elixir", UseElixirItem::new);
    public static final RegistryObject<Item> USE_POWER_ELIXIR = REGISTRY.register("use_power_elixir", UsePowerElixirItem::new);
    // special
    public static final RegistryObject<Item> USE_ALL_CURE_POTION = REGISTRY.register("use_all_cure_potion", UseAllCurePotionItem::new);

    /* cube */
    public static final RegistryObject<Item> USE_OCCULT_CUBE = REGISTRY.register("use_occult_cube", UseOccultCubeItem::new);
    public static final RegistryObject<Item> USE_MASTER_CRAFTSMAN_CUBE = REGISTRY.register("use_master_craftsman_cube", UseMasterCraftsmanCubeItem::new);
    public static final RegistryObject<Item> USE_RED_CUBE = REGISTRY.register("use_red_cube", UseRedCubeItem::new);
    public static final RegistryObject<Item> USE_BLACK_CUBE = REGISTRY.register("use_black_cube", UseBlackCubeItem::new);
    public static final RegistryObject<Item> USE_MEISTER_CUBE = REGISTRY.register("use_meister_cube", UseMeisterCubeItem::new);

    /* scroll */
    public static final RegistryObject<Item> USE_RARE_POTENTIAL_SCROLL = REGISTRY.register("use_rare_potential_scroll", UseRarePotentialScrollItem::new);
    public static final RegistryObject<Item> USE_EPIC_POTENTIAL_SCROLL = REGISTRY.register("use_epic_potential_scroll", UseEpicPotentialScrollItem::new);
    public static final RegistryObject<Item> USE_UNIQUE_POTENTIAL_SCROLL = REGISTRY.register("use_unique_potential_scroll", UseUniquePotentialScrollItem::new);
    public static final RegistryObject<Item> USE_LEGENDARY_POTENTIAL_SCROLL = REGISTRY.register("use_legendary_potential_scroll", UseLegendaryPotentialScrollItem::new);

    /* shuriken */
    public static final RegistryObject<Item> UES_SUBI_THROWING_STARS = REGISTRY.register("use_subi_throwing_stars", UseSubiThrowingStarsItem::new);
    public static final RegistryObject<Item> USE_STEELY_THROWING_KNIVES = REGISTRY.register("use_steely_throwing_knives", UseSteelyThrowingKnivesItem::new);
    public static final RegistryObject<Item> USE_BALANCED_FURY = REGISTRY.register("use_balanced_fury", UseBalancedFuryItem::new);

    /* etc */
    public static final RegistryObject<Item> ETC_MESO_TINY = REGISTRY.register("etc_meso_tiny", EtcMesoTinyItem::new);
    public static final RegistryObject<Item> ETC_MESO_SMALL = REGISTRY.register("etc_meso_small", EtcMesoSmallItem::new);
    public static final RegistryObject<Item> ETC_MESO_MEDIUM = REGISTRY.register("etc_meso_medium", EtcMesoMediumItem::new);
    public static final RegistryObject<Item> ETC_MESO_LARGE = REGISTRY.register("etc_meso_large", EtcMesoLargeItem::new);
    public static final RegistryObject<Item> ETC_MAPLE_LEAF = REGISTRY.register("etc_maple_leaf", EtcMapleLeafItem::new);

    /* weapon */
    // claw
    public static final RegistryObject<Item> CLAW_GARNIER = REGISTRY.register("claw_garnier", ClawGarnierItem::new);
    public static final RegistryObject<Item> CLAW_BRONZE_IGOR = REGISTRY.register("claw_bronze_igor", ClawBronzeIgorItem::new);
    public static final RegistryObject<Item> CLAW_MAPLE_KANDAYO = REGISTRY.register("claw_maple_kandayo", ClawMapleKandayoItem::new);
    // bow
    public static final RegistryObject<Item> BOW_HUNTERS_BOW = REGISTRY.register("bow_hunters_bow", BowHuntersBowItem::new);
    public static final RegistryObject<Item> BOW_MAPLE_BOW = REGISTRY.register("bow_maple_bow", BowMapleBowItem::new);
    // sword
    public static final RegistryObject<Item> SWORD_SWORD = REGISTRY.register("sword_sword", SwordSwordItem::new);
    public static final RegistryObject<Item> SWORD_STONETOOTH_SWORD = REGISTRY.register("sword_stonetooth_sword", SwordStonetoothSwordItem::new);
    public static final RegistryObject<Item> SWORD_MAPLE_SWORD = REGISTRY.register("sword_maple_sword", SwordMapleSwordItem::new);
    // spear
    public static final RegistryObject<Item> SPEAR_FORK_ON_STICK = REGISTRY.register("spear_fork_on_stick", SpearForkOnStick::new);

    /* armor */
    // hat
    public static final RegistryObject<Item> HAT_RED_HEADBAND = REGISTRY.register("hat_red_headband", HatRedHeadbandItem::new);
    public static final RegistryObject<Item> HAT_WIZET_HAT = REGISTRY.register("hat_wizet_hat", HatWizetHatItem::new);
    // top
    public static final RegistryObject<Item> TOP_ORANGE_SPORTS_TSHIRT = REGISTRY.register("top_orange_sports_tshirt", TopOrangeSportsTshirtItem::new);
    // bottom
    public static final RegistryObject<Item> BOTTOM_BLUE_JEAN_SHORTS = REGISTRY.register("bottom_blue_jean_shorts", BottomBlueJeanShortsItem::new);
    // shoes
    public static final RegistryObject<Item> SHOES_RED_RUBBER_BOOTS = REGISTRY.register("shoes_red_rubber_boots", ShoesRedRubberBoots::new);
}
