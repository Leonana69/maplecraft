package net.maplecraft.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.CubeGUIMenuButtonMessage;
import net.maplecraft.world.inventory.CubeGUIMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.client.gui.components.Button;

import java.util.HashMap;

public class CubeGUIMenuScreen extends AbstractContainerScreen<CubeGUIMenu> {
//    private final static HashMap<String, Object> guistate = CubeGUIMenu.guistate;
//    private final Level world;
    private final int x, y, z;
    private final Player entity;

    public CubeGUIMenuScreen(CubeGUIMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
//        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 182;
        this.imageHeight = 166;
    }

    private static final ResourceLocation texture = new ResourceLocation("maplecraft:textures/screens/cube_gui_menu.png");

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, texture);
        GuiComponent.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            assert this.minecraft != null;
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }


    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, "Equip", 9, 19, -12829636);
        this.font.draw(poseStack, "Cube", 9, 51, -12829636);
        this.font.draw(poseStack, "Potential1", 54, 30, -12829636);
        this.font.draw(poseStack, "Potential2", 54, 43, -12829636);
        this.font.draw(poseStack, "Potential3", 54, 57, -12829636);
        this.font.draw(poseStack, "Reconfigure Equip Potentials", 17, 4, -12829636);
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    @Override
    public void init() {
        super.init();
        assert this.minecraft != null;
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.addRenderableWidget(new Button(this.leftPos + 131, this.topPos + 34, 40, 20, Component.literal("USE"), e -> {
            // TODO: maybe remove this
            MapleCraftMod.PACKET_HANDLER.sendToServer(new CubeGUIMenuButtonMessage(0, x, y, z));
            CubeGUIMenuButtonMessage.handleButtonAction(entity, 0, x, y, z);
        }));
    }
}

