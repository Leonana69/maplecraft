package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.block.AdvancedMonsterCrystalOreBlock;
import net.maplecraft.block.BasicMonsterCrystalOreBlock;
import net.maplecraft.block.IntermediateMonsterCrystalOreBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksInit {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MapleCraftMod.MODID);
    public static final RegistryObject<Block> BASIC_MONSTER_CRYSTAL_ORE = REGISTRY.register("basic_monster_crystal_ore",
            BasicMonsterCrystalOreBlock::new);
    public static final RegistryObject<Block> INTERMEDIATE_MONSTER_CRYSTAL_ORE = REGISTRY.register("intermediate_monster_crystal_ore",
            IntermediateMonsterCrystalOreBlock::new);
    public static final RegistryObject<Block> ADVANCED_MONSTER_CRYSTAL_ORE = REGISTRY.register("advanced_monster_crystal_ore",
            AdvancedMonsterCrystalOreBlock::new);
}
