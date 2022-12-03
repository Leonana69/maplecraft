package net.maplecraft.client.overlay;

import net.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import java.util.Vector;
import static net.maplecraft.client.overlay.CustomVanillaBarOverlay.Side.LEFT;

public enum CustomVanillaBarOverlay {
    MANA(LEFT, "playerManaPoints", 10, "textures/screens/mana_bar_vanilla_icon.png");

    public static class IntPoint {
        public int x;
        public int y;
        public IntPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public IntPoint() {
            x = y = 0;
        }
    }
    public final String variableName;
    public final int maxBarIconCount;
    public final Vector<IntPoint> barOffset;
    public final ResourceLocation barIcon;
    enum Side {
        LEFT,
        RIGHT,
    }
    public final Side side;

    CustomVanillaBarOverlay(Side side, String variableName, int maxBarIconCount, String iconFileName) {
        this.side = side;
        this.variableName = variableName;
        this.maxBarIconCount = maxBarIconCount;
        this.barOffset = new Vector<>(maxBarIconCount);
        this.barOffset.setSize(maxBarIconCount);
        this.barIcon = new ResourceLocation(MapleCraftMod.MODID, iconFileName);
    }
}
