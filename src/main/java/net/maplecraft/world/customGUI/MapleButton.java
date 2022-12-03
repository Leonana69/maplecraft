package net.maplecraft.world.customGUI;

import net.maplecraft.MapleCraftMod;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;

public class MapleButton extends ImageButton {
    private static final int textureWidth = 32;
    private static final int textureHeight = 24;

    private static ResourceLocation texture = new ResourceLocation(MapleCraftMod.MODID, "textures/screens/buttons.png");

    public MapleButton(int x, int y, int textureOffsetX, int textureOffsetY, OnPress callBack) {
        super(x, y, 16, 12, textureOffsetX, textureOffsetY, textureHeight / 2, texture, textureWidth, textureHeight, callBack);
    }
}
