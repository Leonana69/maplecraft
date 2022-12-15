package net.maplecraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeTier;

import java.util.List;

import static net.minecraftforge.common.TierSortingRegistry.getSortedTiers;

public class MapleCrystalBlock extends Block {
    public int level;
    public MapleCrystalBlock(int level) {
        super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2f, 10f).requiresCorrectToolForDrops());
        this.level = level;

    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 1;
    }
}
