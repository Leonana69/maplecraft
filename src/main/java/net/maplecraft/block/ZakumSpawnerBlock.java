package net.maplecraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class ZakumSpawnerBlock extends Block {
    public ZakumSpawnerBlock() {
        super(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GLASS).strength(2f, 10f).requiresCorrectToolForDrops());
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 10;
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        return false;
    }
}
