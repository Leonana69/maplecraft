package net.maplecraft.block;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.MapleCrystalBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.Collections;
import java.util.List;

public class AdvancedMonsterCrystalOreBlock extends MapleCrystalBlock {
    public AdvancedMonsterCrystalOreBlock() {
        super(1);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(ItemsInit.ETC_ADVANCED_MONSTER_CRYSTAL.get(), 2));
    }
}
