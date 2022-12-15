package net.maplecraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class MapleCrystalBlock extends Block {
    public MapleCrystalBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .sound(SoundType.STONE)
                .strength(2f, 10f)
                .requiresCorrectToolForDrops()
                .lightLevel(ll -> 1));
    }
}
