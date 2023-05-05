package com.maplecraft.inventory;

import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.world.item.ItemStack;

public class MushroomBookViewScreen extends BookViewScreen {
    public MushroomBookViewScreen(ItemStack itemStack) {
        super(new WrittenBookAccess(itemStack));
    }
}
