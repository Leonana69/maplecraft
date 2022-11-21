package net.maplecraft.client.screens;

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
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import net.maplecraft.client.screens.CustomVanillaBar.IntPoint;

@OnlyIn(Dist.CLIENT)
public class GeneralBarOverlay {
    static ResourceLocation PLAYER_HEALTH_ELEMENT = new ResourceLocation("minecraft", "player_health");
    static ResourceLocation FOOD_LEVEL_ELEMENT = new ResourceLocation("minecraft", "food_level");

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new GeneralBarOverlay());
    }

    @SubscribeEvent
    public void onRenderGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay() == GuiOverlayManager.findOverlay(PLAYER_HEALTH_ELEMENT)) {
            Minecraft mc = Minecraft.getInstance();
            ForgeGui gui = (ForgeGui) mc.gui;

            assert mc.player != null;
            boolean isMounted = mc.player.getVehicle() instanceof LivingEntity;
            if (!isMounted && !mc.options.hideGui && gui.shouldDrawSurvivalElements()) {
                renderCustomVanillaBar(event, CustomVanillaBar.MANA);
            }
        }
    }

    public void renderCustomVanillaBar(RenderGuiOverlayEvent.Pre event, CustomVanillaBar info) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ForgeGui gui = (ForgeGui) mc.gui;

        int startX;
        int deltaX;
        int startY = event.getWindow().getGuiScaledHeight() - gui.rightHeight;
        int rowHeight;
        int maxRow = (int) Math.ceil((float) info.maxBarIconCount / 10);
        if (info.side == CustomVanillaBar.Side.LEFT) {
            startX = event.getWindow().getGuiScaledWidth() / 2 - 91;
            deltaX = 8;
            assert player != null;
            int healthBarCount = (int) Math.ceil((player.getMaxHealth()) / 2.0F);
            int healthBarRows = (int) Math.ceil((float) healthBarCount / 10.0F);
            rowHeight = Math.max(10 - (healthBarRows - 2), 3);
            gui.leftHeight = 39 + rowHeight * maxRow;
        } else {
            startX = event.getWindow().getGuiScaledWidth() / 2 + 83;
            deltaX = -8;
            rowHeight = 10;
            gui.rightHeight = 39 + rowHeight * maxRow;
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

        for (int i = 0; i < info.maxBarIconCount; i++) {
            int u;
            int v = 0;
            assert player != null;
            double value = (double) Variables.get(player, info.variableName);
            double curIconValue = value / 2.0D - i;

            if (curIconValue >= 1.0D) {
                u = 0 * iconSize;
            } else if (curIconValue > 0.49D) {
                u = 1 * iconSize;
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
