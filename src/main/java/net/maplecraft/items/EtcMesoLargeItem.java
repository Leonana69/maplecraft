package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class EtcMesoLargeItem extends Item {
    public EtcMesoLargeItem() {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                .stacksTo(64)
                .rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.maplecraft.etc_meso_large_description"));
    }
}