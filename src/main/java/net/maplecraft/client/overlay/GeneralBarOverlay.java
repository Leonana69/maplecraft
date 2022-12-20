package net.maplecraft.client.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.maplecraft.network.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import net.maplecraft.client.overlay.CustomVanillaBarOverlay.IntPoint;

@OnlyIn(Dist.CLIENT)
public class GeneralBarOverlay {
    static ResourceLocation PLAYER_HEALTH_ELEMENT = new ResourceLocation("minecraft", "player_health");
    static ResourceLocation FOOD_LEVEL_ELEMENT = new ResourceLocation("minecraft", "food_level");

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new GeneralBarOverlay());
    }

    @SubscribeEvent
    public void onRenderGuiOverlayPost(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        ForgeGui gui = (ForgeGui) mc.gui;
        assert mc.player != null;
        boolean isMounted = mc.player.getVehicle() instanceof LivingEntity;
        if (!isMounted && !mc.options.hideGui && gui.shouldDrawSurvivalElements()) {
            if (event.getOverlay().id().equals(PLAYER_HEALTH_ELEMENT)) {
                renderCustomVanillaBar(event, CustomVanillaBarOverlay.MANA);
            }
        }
    }

    public static void renderCustomVanillaBar(RenderGuiOverlayEvent event, CustomVanillaBarOverlay info) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ForgeGui gui = (ForgeGui) mc.gui;

        int startX, startY;
        int deltaX;
        int rowHeight;
        int maxRow = (int) Math.ceil((float) info.maxBarIconCount / 10);
        if (info.side == CustomVanillaBarOverlay.Side.LEFT) {
            startX = event.getWindow().getGuiScaledWidth() / 2 - 91;
            startY = event.getWindow().getGuiScaledHeight() - gui.leftHeight;
            deltaX = 8;
            assert player != null;
            int healthBarCount = (int) Math.ceil((player.getMaxHealth()) / 2.0F);
            int healthBarRows = (int) Math.ceil((float) healthBarCount / 10.0F);
            rowHeight = Math.min(Math.max(10 - (healthBarRows - 2), 3), Math.max(10 - (maxRow - 2), 3));
            gui.leftHeight += rowHeight * (maxRow - 1) + 10;
        } else {
            startX = event.getWindow().getGuiScaledWidth() / 2 + 83;
            startY = event.getWindow().getGuiScaledHeight() - gui.rightHeight;
            deltaX = -8;
            rowHeight = Math.max(10 - (maxRow - 2), 3);
            gui.rightHeight += rowHeight * (maxRow - 1) + 10;
        }

        for (int i = 0; i < info.maxBarIconCount; i++) {
            int row = (int) Math.ceil((float) (i + 1) / 10) - 1;
            int x = startX + (i % 10) * deltaX;
            int y = startY - row * 10;
            info.barOffset.set(i, new IntPoint(x, y));
        }

        int textureWidth = 27;
        int textureHeight = 9;
        int iconSize = 9;

        enableAlpha(1.0F);
        RenderSystem.setShaderTexture(0, info.barIcon);
        assert player != null;
        double value = (double) Variables.get(player, info.variableName);
        for (int i = 0; i < info.maxBarIconCount; i++) {
            int u;
            int v = 0;

            System.out.println("mana: " + value);
            double curIconValue = value / 2.0D - i;

            if (curIconValue >= 1.0D) {
                u = 0;
            } else if (curIconValue > 0.49D) {
                u = iconSize;
            } else {
                u = 2 * iconSize;
            }

            GuiComponent.blit(event.getPoseStack(),
                    info.barOffset.get(i).x, info.barOffset.get(i).y,
                    u, v,
                    iconSize, iconSize,
                    textureWidth, textureHeight);
        }
        disableAlpha();
    }

    public static void enableAlpha(float alpha) {
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void disableAlpha() {
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
