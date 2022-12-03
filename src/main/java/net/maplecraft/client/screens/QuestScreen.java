package net.maplecraft.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.CubeScreenButtonMessageHandler;
import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.EquipWiseData;
import net.maplecraft.utils.IBaseEquip;
import net.maplecraft.world.customGUI.CubeMenu;
import net.maplecraft.world.customGUI.QuestMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class QuestScreen extends AbstractContainerScreen<QuestMenu> {
    private final Player entity;

    public QuestScreen(QuestMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    private static final ResourceLocation texture = new ResourceLocation("maplecraft:textures/screens/cube_screen.png");

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTicks, int gx, int gy) {
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
            assert this.minecraft.player != null;
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
        this.font.draw(poseStack, "Equip", 7, 19, -12829636);
        this.font.draw(poseStack, "Reconfigure Equip Potentials", 16, 5, -12829636);
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
        this.addRenderableWidget(new Button(this.leftPos + 128, this.topPos + 37, 40, 20, Component.literal("USE"), e -> {
//            MapleCraftMod.PACKET_HANDLER.sendToServer(new CubeScreenButtonMessageHandler(0, guiType));
//            CubeScreenButtonMessageHandler.handleButtonAction(entity, 0, guiType);
        }) {
            @Override
            public void render(PoseStack poseStack, int gx, int gy, float ticks) {
                super.render(poseStack, gx, gy, ticks);
            }
        });

    }
}

