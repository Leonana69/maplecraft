package net.maplecraft.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.api.event.GeneralBarRenderEvent;
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

import java.util.Vector;

@OnlyIn(Dist.CLIENT)
public class GeneralBarOverlay {
    public static int getGuiScaledWidth = 0;
    public static int getGuiScaledHeight = 0;

    public static class IntPoint {
        public int x;
        public int y;
        public IntPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static ResourceLocation PLAYER_HEALTH_ELEMENT = new ResourceLocation("minecraft", "player_health");

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new GeneralBarOverlay());
    }

    /* mana bar */
    private static final int manaBarMaxCount = 12;
    public static final Vector<IntPoint> manaBarOffsets = new Vector<>();
    private static final ResourceLocation manaBarIcon = new ResourceLocation(MapleCraftMod.MODID, "textures/screens/mana_bar_icon.png");

    @SubscribeEvent
    public void onRenderGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay() == GuiOverlayManager.findOverlay(PLAYER_HEALTH_ELEMENT)) {
            Minecraft mc = Minecraft.getInstance();
            ForgeGui gui = (ForgeGui) mc.gui;
            boolean isMounted = mc.player.getVehicle() instanceof LivingEntity;
            if (!isMounted && !mc.options.hideGui && gui.shouldDrawSurvivalElements()) {
                int mana = (mc.player.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
                        .orElse(new Variables.PlayerVariables())).playerManaPoints;
                renderManaBar(event, mana, manaBarMaxCount, manaBarOffsets, manaBarIcon);
            }
        }
    }

    public void renderManaBar(RenderGuiOverlayEvent.Pre event, int value, int maxCount, Vector<IntPoint> offset, ResourceLocation icon) {
        // TODO: refine start location
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ForgeGui gui = (ForgeGui) mc.gui;
        assert player != null;
        int top = event.getWindow().getGuiScaledHeight() - gui.rightHeight;
        int left = event.getWindow().getGuiScaledWidth() / 2 - 91; // left of health bar

        int healthBarCount = (int) Math.ceil((player.getMaxHealth()) / 2.0F);
        int healthBarRows = (int) Math.ceil((float) healthBarCount / 10.0F);
        int healthBarRowsHeight = 10 * healthBarRows;

        if (offset.size() != maxCount || updateOverlay(event)) {
            offset.setSize(maxCount);
            for (int i = 0; i < maxCount; i++) {
                int row = (int) Math.ceil((float) (i + 1) / 10) - 1;
                int x = left + (i % 10) * 8;
                int y = top - healthBarRowsHeight - row * 10;
                offset.set(i, new IntPoint(x, y));
            }
        }

        // notify everyone the event
        // TODO: maybe unnecessary
        GeneralBarRenderEvent.Mana manaBarRenderEvent = new GeneralBarRenderEvent.Mana(value, left, top, event.getPoseStack());
        if (!manaBarRenderEvent.isCanceled()) {
            MinecraftForge.EVENT_BUS.post(manaBarRenderEvent);
        }

        // start render
        enableAlpha(1.0F);
        RenderSystem.setShaderTexture(0, icon);
        int textureWidth = 27;
        int textureHeight = 9;
        int iconSize = 9;

        for (int i = 0; i < maxCount; i++) {
            int u = 0;
            int v = 0;
            float curIconValue = value / 2.0F - i;
            if (curIconValue >= 1) {
                u = 0;
            } else if (curIconValue > 0.49) {
                u = iconSize;
            } else {
                u = 2 * iconSize;
            }
            GuiComponent.blit(event.getPoseStack(),
                    offset.get(i).x, offset.get(i).y,
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

    public boolean updateOverlay(RenderGuiOverlayEvent event) {
        if (getGuiScaledWidth != event.getWindow().getGuiScaledWidth() ||
            getGuiScaledHeight != event.getWindow().getGuiScaledHeight()) {
            getGuiScaledWidth = event.getWindow().getGuiScaledWidth();
            getGuiScaledHeight = event.getWindow().getGuiScaledHeight();
            return true;
        }
        return false;
    }
}
