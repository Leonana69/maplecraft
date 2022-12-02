package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.entities.boss.zakum.BossZakumSpawnEggItem;
import net.maplecraft.item.accessory.NecklacePendantOfTheSpirit;
import net.maplecraft.item.armor.*;
import net.maplecraft.item.etc.*;
import net.maplecraft.item.skill.*;
import net.maplecraft.item.use.*;
import net.maplecraft.item.weapon.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsInit {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MapleCraftMod.MODID);
    /* creative tab icon */
    public static final RegistryObject<Item> MAPLE_STORY_ICON = REGISTRY.register("maple_story_icon", () -> new Item(new Item.Properties()));

    /* consumable item */
    // hp
    public static final RegistryObject<Item> USE_RED_POTION = REGISTRY.register(UseRedPotionItem.itemName, UseRedPotionItem::new);
    public static final RegistryObject<Item> USE_ORANGE_POTION = REGISTRY.register(UseOrangePotionItem.itemName, UseOrangePotionItem::new);
    public static final RegistryObject<Item> USE_WHITE_POTION = REGISTRY.register(UseWhitePotionItem.itemName, UseWhitePotionItem::new);
    // mana
    public static final RegistryObject<Item> USE_BLUE_POTION = REGISTRY.register(UseBluePotionItem.itemName, UseBluePotionItem::new);
    public static final RegistryObject<Item> USE_MANA_ELIXIR = REGISTRY.register(UseManaElixirItem.itemName, UseManaElixirItem::new);
    // both
    public static final RegistryObject<Item> USE_ELIXIR = REGISTRY.register(UseElixirItem.itemName, UseElixirItem::new);
    public static final RegistryObject<Item> USE_POWER_ELIXIR = REGISTRY.register(UsePowerElixirItem.itemName, UsePowerElixirItem::new);
    // special
    public static final RegistryObject<Item> USE_ALL_CURE_POTION = REGISTRY.register(UseAllCurePotionItem.itemName, UseAllCurePotionItem::new);

    /* cube */
    public static final RegistryObject<Item> USE_OCCULT_CUBE = REGISTRY.register(UseOccultCubeItem.itemName, UseOccultCubeItem::new);
    public static final RegistryObject<Item> USE_MASTER_CRAFTSMAN_CUBE = REGISTRY.register(UseMasterCraftsmanCubeItem.itemName, UseMasterCraftsmanCubeItem::new);
    public static final RegistryObject<Item> USE_RED_CUBE = REGISTRY.register(UseRedCubeItem.itemName, UseRedCubeItem::new);
    public static final RegistryObject<Item> USE_BLACK_CUBE = REGISTRY.register(UseBlackCubeItem.itemName, UseBlackCubeItem::new);
    public static final RegistryObject<Item> USE_MEISTER_CUBE = REGISTRY.register(UseMeisterCubeItem.itemName, UseMeisterCubeItem::new);

    /* scroll */
    public static final RegistryObject<Item> USE_RARE_POTENTIAL_SCROLL = REGISTRY.register(UseRarePotentialScrollItem.itemName, UseRarePotentialScrollItem::new);
    public static final RegistryObject<Item> USE_EPIC_POTENTIAL_SCROLL = REGISTRY.register(UseEpicPotentialScrollItem.itemName, UseEpicPotentialScrollItem::new);
    public static final RegistryObject<Item> USE_UNIQUE_POTENTIAL_SCROLL = REGISTRY.register(UseUniquePotentialScrollItem.itemName, UseUniquePotentialScrollItem::new);
    public static final RegistryObject<Item> USE_LEGENDARY_POTENTIAL_SCROLL = REGISTRY.register(UseLegendaryPotentialScrollItem.itemName, UseLegendaryPotentialScrollItem::new);

    /* shuriken */
    public static final RegistryObject<Item> UES_SUBI_THROWING_STARS = REGISTRY.register(UseSubiThrowingStarsItem.itemName, UseSubiThrowingStarsItem::new);
    public static final RegistryObject<Item> USE_STEELY_THROWING_KNIVES = REGISTRY.register(UseSteelyThrowingKnivesItem.itemName, UseSteelyThrowingKnivesItem::new);
    public static final RegistryObject<Item> USE_BALANCED_FURY = REGISTRY.register(UseBalancedFuryItem.itemName, UseBalancedFuryItem::new);

    /* arrow */
    public static final RegistryObject<Item> USE_ARROW_FOR_BOW = REGISTRY.register(UseArrowForBowItem.itemName, UseArrowForBowItem::new);
    public static final RegistryObject<Item> USE_BRONZE_ARROW_FOR_BOW = REGISTRY.register(UseBronzeArrowForBowItem.itemName, UseBronzeArrowForBowItem::new);
    public static final RegistryObject<Item> USE_DIAMOND_ARROW_FOR_BOW = REGISTRY.register(UseDiamondArrowForBowItem.itemName, UseDiamondArrowForBowItem::new);

    /* etc */
    public static final RegistryObject<Item> ETC_MESO_TINY = REGISTRY.register(EtcMesoTinyItem.itemName, EtcMesoTinyItem::new);
    public static final RegistryObject<Item> ETC_MESO_SMALL = REGISTRY.register(EtcMesoSmallItem.itemName, EtcMesoSmallItem::new);
    public static final RegistryObject<Item> ETC_MESO_MEDIUM = REGISTRY.register(EtcMesoMediumItem.itemName, EtcMesoMediumItem::new);
    public static final RegistryObject<Item> ETC_MESO_LARGE = REGISTRY.register(EtcMesoLargeItem.itemName, EtcMesoLargeItem::new);
    public static final RegistryObject<Item> ETC_MAPLE_LEAF = REGISTRY.register(EtcMapleLeafItem.itemName, EtcMapleLeafItem::new);
    public static final RegistryObject<Item> ETC_EYE_OF_FIRE = REGISTRY.register(EtcEyeOfFireItem.itemName, EtcEyeOfFireItem::new);

    public static final RegistryObject<Item> ETC_BASIC_MONSTER_CRYSTAL = REGISTRY.register(EtcBasicMonsterCrystalItem.itemName, EtcBasicMonsterCrystalItem::new);
    public static final RegistryObject<Item> ETC_INTERMEDIATE_MONSTER_CRYSTAL = REGISTRY.register(EtcIntermediateMonsterCrystalItem.itemName, EtcIntermediateMonsterCrystalItem::new);
    public static final RegistryObject<Item> ETC_ADVANCED_MONSTER_CRYSTAL = REGISTRY.register(EtcAdvancedMonsterCrystalItem.itemName, EtcAdvancedMonsterCrystalItem::new);

    public static final RegistryObject<Item> BLOCK_BASIC_MONSTER_CRYSTAL_ORE = block(BlocksInit.BASIC_MONSTER_CRYSTAL_ORE, TabsInit.TAB_MAPLE_CRAFT);
    public static final RegistryObject<Item> BLOCK_INTERMEDIATE_MONSTER_CRYSTAL_ORE = block(BlocksInit.INTERMEDIATE_MONSTER_CRYSTAL_ORE, TabsInit.TAB_MAPLE_CRAFT);
    public static final RegistryObject<Item> BLOCK_ADVANCED_MONSTER_CRYSTAL_ORE = block(BlocksInit.ADVANCED_MONSTER_CRYSTAL_ORE, TabsInit.TAB_MAPLE_CRAFT);

    /* weapon */
    // claw
    public static final RegistryObject<Item> CLAW_GARNIER = REGISTRY.register(ClawGarnierItem.itemName, ClawGarnierItem::new);
    public static final RegistryObject<Item> CLAW_BRONZE_IGOR = REGISTRY.register(ClawBronzeIgorItem.itemName, ClawBronzeIgorItem::new);
    public static final RegistryObject<Item> CLAW_BLACK_SCARAB = REGISTRY.register(ClawBlackScarabItem.itemName, ClawBlackScarabItem::new);
    public static final RegistryObject<Item> CLAW_MAPLE_KANDAYO = REGISTRY.register(ClawMapleKandayoItem.itemName, ClawMapleKandayoItem::new);

    // dagger
    public static final RegistryObject<Item> DAGGER_RAZOR = REGISTRY.register(DaggerRazorItem.itemName, DaggerRazorItem::new);
    public static final RegistryObject<Item> DAGGER_STINGER = REGISTRY.register(DaggerStingerItem.itemName, DaggerStingerItem::new);
    public static final RegistryObject<Item> DAGGER_KOREAN_FAN = REGISTRY.register(DaggerKoreanFanItem.itemName, DaggerKoreanFanItem::new);
    public static final RegistryObject<Item> DAGGER_MAPLE_WAGNER = REGISTRY.register(DaggerMapleWagnerItem.itemName, DaggerMapleWagnerItem::new);

    // bow
    public static final RegistryObject<Item> BOW_WAR_BOW = REGISTRY.register(BowWarBowItem.itemName, BowWarBowItem::new);
    public static final RegistryObject<Item> BOW_HUNTERS_BOW = REGISTRY.register(BowHuntersBowItem.itemName, BowHuntersBowItem::new);
    public static final RegistryObject<Item> BOW_RYDEN = REGISTRY.register(BowRydenItem.itemName, BowRydenItem::new);
    public static final RegistryObject<Item> BOW_MAPLE_BOW = REGISTRY.register(BowMapleBowItem.itemName, BowMapleBowItem::new);

    // crossbow
    public static final RegistryObject<Item> CROSSBOW_CROSSBOW = REGISTRY.register(CrossbowCrossbowItem.itemName, CrossbowCrossbowItem::new);
    public static final RegistryObject<Item> CROSSBOW_BALANCHE = REGISTRY.register(CrossbowBalancheItem.itemName, CrossbowBalancheItem::new);
    public static final RegistryObject<Item> CROSSBOW_GOLDEN_RAVEN = REGISTRY.register(CrossbowGoldenRavenItem.itemName, CrossbowGoldenRavenItem::new);
    public static final RegistryObject<Item> CROSSBOW_MAPLE_CROSSBOW = REGISTRY.register(CrossbowMapleCrossbowItem.itemName, CrossbowMapleCrossbowItem::new);

    // sword
    public static final RegistryObject<Item> SWORD_SWORD = REGISTRY.register(SwordSwordItem.itemName, SwordSwordItem::new);
    public static final RegistryObject<Item> SWORD_SABRE = REGISTRY.register(SwordSabreItem.itemName, SwordSabreItem::new);
    public static final RegistryObject<Item> SWORD_STONETOOTH_SWORD = REGISTRY.register(SwordStonetoothSwordItem.itemName, SwordStonetoothSwordItem::new);
    public static final RegistryObject<Item> SWORD_MAPLE_SWORD = REGISTRY.register(SwordMapleSwordItem.itemName, SwordMapleSwordItem::new);

    // spear
    public static final RegistryObject<Item> SPEAR_SPEAR = REGISTRY.register(SpearSpearItem.itemName, SpearSpearItem::new);
    public static final RegistryObject<Item> SPEAR_FORK_ON_STICK = REGISTRY.register(SpearForkOnStickItem.itemName, SpearForkOnStickItem::new);
    public static final RegistryObject<Item> SPEAR_FISH_SPEAR = REGISTRY.register(SpearFishSpearItem.itemName, SpearFishSpearItem::new);
    public static final RegistryObject<Item> SPEAR_MAPLE_IMPALER = REGISTRY.register(SpearMapleImpalerItem.itemName, SpearMapleImpalerItem::new);

    // wand
    public static final RegistryObject<Item> WAND_HARDWOOD_WAND = REGISTRY.register(WandHardwoodWandItem.itemName, WandHardwoodWandItem::new);
    public static final RegistryObject<Item> WAND_WIZARD_STAFF = REGISTRY.register(WandWizardStaffItem.itemName, WandWizardStaffItem::new);
    public static final RegistryObject<Item> WAND_CROMI = REGISTRY.register(WandCromiItem.itemName, WandCromiItem::new);
    public static final RegistryObject<Item> WAND_MAPLE_LAMA_STAFF = REGISTRY.register(WandMapleLamaStaffItem.itemName, WandMapleLamaStaffItem::new);

    /* armor */
    // hat
    public static final RegistryObject<Item> HAT_RED_HEADBAND = REGISTRY.register(HatRedHeadbandItem.itemName, HatRedHeadbandItem::new);
    public static final RegistryObject<Item> HAT_WIZET_HAT = REGISTRY.register(HatWizetHatItem.itemName, HatWizetHatItem::new);
    public static final RegistryObject<Item> HAT_ZAKUM_HELMET = REGISTRY.register(HatZakumHelmetItem.itemName, HatZakumHelmetItem::new);
    // top
    public static final RegistryObject<Item> TOP_ORANGE_SPORTS_TSHIRT = REGISTRY.register(TopOrangeSportsTshirtItem.itemName, TopOrangeSportsTshirtItem::new);
    // bottom
    public static final RegistryObject<Item> BOTTOM_BLUE_JEAN_SHORTS = REGISTRY.register(BottomBlueJeanShortsItem.itemName, BottomBlueJeanShortsItem::new);
    // shoes
    public static final RegistryObject<Item> SHOES_RED_RUBBER_BOOTS = REGISTRY.register(ShoesRedRubberBootsItem.itemName, ShoesRedRubberBootsItem::new);

    /* accessory */
    public static final RegistryObject<Item> NECKLACE_PENDANT_OF_THE_SPIRIT = REGISTRY.register(NecklacePendantOfTheSpirit.itemName, NecklacePendantOfTheSpirit::new);


    /* skill */
    // warrior
    public static final RegistryObject<Item> SKILL_POWER_STRIKE = REGISTRY.register(SkillPowerStrike.itemName, SkillPowerStrike::new);
    public static final RegistryObject<Item> SKILL_SLASH_BLAST = REGISTRY.register(SkillSlashBlast.itemName, SkillSlashBlast::new);

    public static final RegistryObject<Item> SKILL_IRON_WILL = REGISTRY.register(SkillIronWill.itemName, SkillIronWill::new);
    public static final RegistryObject<Item> SKILL_DRAGON_FURY = REGISTRY.register(SkillDragonFury.itemName, SkillDragonFury::new);

    // magician
    public static final RegistryObject<Item> SKILL_TELEPORT = REGISTRY.register(SkillTeleport.itemName, SkillTeleport::new);
    public static final RegistryObject<Item> SKILL_MAGIC_CLAW = REGISTRY.register(SkillMagicClaw.itemName, SkillMagicClaw::new);

    public static final RegistryObject<Item> SKILL_FIRE_ARROW = REGISTRY.register(SkillFireArrow.itemName, SkillFireArrow::new);
    public static final RegistryObject<Item> SKILL_POISON_BRACE = REGISTRY.register(SkillPoisonBrace.itemName, SkillPoisonBrace::new);

    public static final RegistryObject<Item> SKILL_COLD_BEAM = REGISTRY.register(SkillColdBeam.itemName, SkillColdBeam::new);
    public static final RegistryObject<Item> SKILL_THUNDERBOLT = REGISTRY.register(SkillThunderbolt.itemName, SkillThunderbolt::new);

    public static final RegistryObject<Item> SKILL_HEAL = REGISTRY.register(SkillHeal.itemName, SkillHeal::new);
    public static final RegistryObject<Item> SKILL_HOLY_ARROW = REGISTRY.register(SkillHolyArrow.itemName, SkillHolyArrow::new);

    // bowman
    public static final RegistryObject<Item> SKILL_ARROW_BLOW = REGISTRY.register(SkillArrowBlow.itemName, SkillArrowBlow::new);
    public static final RegistryObject<Item> SKILL_DOUBLE_SHOT = REGISTRY.register(SkillDoubleShot.itemName, SkillDoubleShot::new);

    public static final RegistryObject<Item> SKILL_ARROW_BOMB = REGISTRY.register(SkillArrowBomb.itemName, SkillArrowBomb::new);

    public static final RegistryObject<Item> SKILL_IRON_ARROW = REGISTRY.register(SkillIronArrow.itemName, SkillIronArrow::new);

    public static final RegistryObject<Item> SKILL_STRAFE = REGISTRY.register(SkillStrafe.itemName, SkillStrafe::new);
    public static final RegistryObject<Item> SKILL_ARROW_RAIN = REGISTRY.register(SkillArrowRain.itemName, SkillArrowRain::new);

    // thief
    public static final RegistryObject<Item> SKILL_LUCKY_SEVEN = REGISTRY.register(SkillLuckySeven.itemName, SkillLuckySeven::new);
    public static final RegistryObject<Item> SKILL_DOUBLE_STAB = REGISTRY.register(SkillDoubleStab.itemName, SkillDoubleStab::new);

    public static final RegistryObject<Item> SKILL_HASTE = REGISTRY.register(SkillHaste.itemName, SkillHaste::new);
    public static final RegistryObject<Item> SKILL_DRAIN = REGISTRY.register(SkillDrain.itemName, SkillDrain::new);

    public static final RegistryObject<Item> SKILL_SAVAGE_BLOW = REGISTRY.register(SkillSavageBlow.itemName, SkillSavageBlow::new);


    // spawn egg
    public static final RegistryObject<Item> BOSS_ZAKUM_SPAWN_EGG = REGISTRY.register(BossZakumSpawnEggItem.itemName, BossZakumSpawnEggItem::new);

    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
