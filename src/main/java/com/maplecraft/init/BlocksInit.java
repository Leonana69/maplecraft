package com.maplecraft.init;

import com.maplecraft.MapleCraftMod;
import com.maplecraft.block.AdvancedMonsterCrystalOre;
import com.maplecraft.block.BasicMonsterCrystalOre;
import com.maplecraft.block.IntermediateMonsterCrystalOre;
import com.maplecraft.block.ZakumSpawnerCore;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksInit {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MapleCraftMod.MODID);
    public static final RegistryObject<Block> BASIC_MONSTER_CRYSTAL_ORE = REGISTRY.register(BasicMonsterCrystalOre.blockName, BasicMonsterCrystalOre::new);
    public static final RegistryObject<Block> INTERMEDIATE_MONSTER_CRYSTAL_ORE = REGISTRY.register(IntermediateMonsterCrystalOre.blockName, IntermediateMonsterCrystalOre::new);
    public static final RegistryObject<Block> ADVANCED_MONSTER_CRYSTAL_ORE = REGISTRY.register(AdvancedMonsterCrystalOre.blockName, AdvancedMonsterCrystalOre::new);
    public static final RegistryObject<Block> ZAKUM_SPAWNER_CORE = REGISTRY.register(ZakumSpawnerCore.blockName, ZakumSpawnerCore::new);
}
