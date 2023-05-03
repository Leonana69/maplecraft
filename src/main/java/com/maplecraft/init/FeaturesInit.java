package com.maplecraft.init;

import com.maplecraft.worldgen.feature.AdvancedMonsterCrystalOreFeature;
import com.maplecraft.worldgen.feature.BasicMonsterCrystalOreFeature;
import com.maplecraft.worldgen.feature.IntermediateMonsterCrystalOreFeature;
import com.maplecraft.MapleCraftMod;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeaturesInit {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, MapleCraftMod.MODID);
    public static final RegistryObject<Feature<?>> BASIC_MONSTER_CRYSTAL_ORE_FEATURE
            = REGISTRY.register("basic_monster_crystal_ore_feature", BasicMonsterCrystalOreFeature::feature);
    public static final RegistryObject<Feature<?>> INTERMEDIATE_MONSTER_CRYSTAL_ORE_FEATURE
            = REGISTRY.register("intermediate_monster_crystal_ore_feature", IntermediateMonsterCrystalOreFeature::feature);
    public static final RegistryObject<Feature<?>> ADVANCED_MONSTER_CRYSTAL_ORE_FEATURE
            = REGISTRY.register("advanced_monster_crystal_ore_feature", AdvancedMonsterCrystalOreFeature::feature);
}
