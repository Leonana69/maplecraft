package net.maplecraft.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.Collections;
import java.util.List;

public class MapleCrystalBlock extends Block {
    public int level;
    public MapleCrystalBlock(int level) {
        super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2f, 10f).requiresCorrectToolForDrops());
        this.level = level;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        if (player.getInventory().getSelected().getItem() instanceof TieredItem tieredItem)
            return tieredItem.getTier().getLevel() >= level;
        return false;
    }
//    @Override
//    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
//        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
//        if (!dropsOriginal.isEmpty())
//            return dropsOriginal;
//        return Collections.singletonList(new ItemStack(drop));
//    }
}
