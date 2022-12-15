package net.maplecraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class ZakumSpawnerCore extends Block {
    public static String blockName = "zakum_spawner_core";
    public ZakumSpawnerCore() {
        super(BlockBehaviour.Properties.of(Material.GRASS)
                .sound(SoundType.GLASS)
                .strength(2f, 10f)
                .requiresCorrectToolForDrops()
                .lightLevel(ll -> 1));
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        return false;
    }
}
