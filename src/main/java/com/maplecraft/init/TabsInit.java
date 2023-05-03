package com.maplecraft.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TabsInit {
    public static CreativeModeTab TAB_MAPLE_CRAFT;
    private static final String path = "textures/gui/container/creative_inventory/tab_";
    private static final String searchTabPath = path + "item_search.png";
    public static void load() {
        TAB_MAPLE_CRAFT = new CreativeModeTab("tabmaple_craft") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ItemsInit.MAPLE_STORY_ICON.get());
            }

            @Override
            public boolean hasSearchBar() {
                return true;
            }
        }.setBackgroundImage(new ResourceLocation(searchTabPath));

    }
}
