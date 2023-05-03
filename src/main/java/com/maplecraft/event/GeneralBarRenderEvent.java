package com.maplecraft.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class GeneralBarRenderEvent extends Event {
    public static class Mana extends GeneralBarRenderEvent {
        public Mana(int mana, int x, int y, PoseStack matrixStack) {
            super(x, y, matrixStack);
            this.mana = mana;
        }

        public final int mana;
    }

    private GeneralBarRenderEvent(int x, int y, PoseStack matrixStack) {
        this.x = x;
        this.y = y;
        this.matrixStack = matrixStack;
    }

    public int x;
    public int y;
    public PoseStack matrixStack;

    @Override
    public boolean isCancelable() {
        return true;
    }
}
