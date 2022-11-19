package net.maplecraft.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class SwordWeaponItem extends WeaponItem {
    public float attackSpeed;

    public SwordWeaponItem(Properties itemProperties, EquipBaseData data) {
        super(itemProperties, data.category(EquipCategory.SWORD));
    }

    @Override
    public boolean canAttackBlock(BlockState blockState, Level world, BlockPos blockPos, Player player) {
        return !player.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return blockState.is(Blocks.COBWEB) ? 15.0F : 1.5F;
    }
}
