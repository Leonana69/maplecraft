package dev.maplecraft.items;

import dev.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MesoSmallEtcItem extends Item {
    public MesoSmallEtcItem() {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                .stacksTo(64)
                .rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("Small amount of meso"));
    }
}
