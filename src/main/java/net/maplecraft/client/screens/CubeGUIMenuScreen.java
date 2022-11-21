package net.maplecraft.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.CubeGUIMenuButtonMessage;
import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.EquipWiseData;
import net.maplecraft.world.customGUI.CubeGUIMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.gui.components.Button;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.maplecraft.network.EquipCapabilitiesProvider.EQUIP_CAPABILITIES;

public class CubeGUIMenuScreen extends AbstractContainerScreen<CubeGUIMenu> {
    private final int x, y, z;
    private final Player entity;

    public static String pCurrent0 = "";
    public static String pCurrent1 = "";
    public static String pCurrent2 = "";
    public static String pAfter0 = "";
    public static String pAfter1 = "";
    public static String pAfter2 = "";
    public static int guiType = 0;

    public CubeGUIMenuScreen(CubeGUIMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 182;
        this.imageHeight = 166;
    }

    private static final ResourceLocation texture = new ResourceLocation("maplecraft:textures/screens/cube_gui_menu.png");

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

        if (guiType == 1) {
            RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/screens/cube_potential_background_old.png"));
            GuiComponent.blit(poseStack, this.leftPos + 35, this.topPos + 19, 0, 0, 60, 36, 60, 36);
            RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/screens/cube_potential_background_new.png"));
            GuiComponent.blit(poseStack, this.leftPos + 105, this.topPos + 19, 0, 0, 60, 36, 60, 36);

        } else {
            RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/screens/cube_potential_background_old.png"));
            GuiComponent.blit(poseStack, this.leftPos + 51, this.topPos + 27, 0, 0, 60, 36, 60, 36);
        }

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
        if (guiType == 1) {
            // black cube
            this.font.draw(poseStack, pCurrent0, 38, 22, -12829636);
            this.font.draw(poseStack, pCurrent1, 38, 34, -12829636);
            this.font.draw(poseStack, pCurrent2, 38, 46, -12829636);
            if (CubeItem.updated) {
                this.font.draw(poseStack, pAfter0, 108, 22, -12829636);
                this.font.draw(poseStack, pAfter1, 108, 34, -12829636);
                this.font.draw(poseStack, pAfter2, 108, 46, -12829636);
            }
        } else {
            this.font.draw(poseStack, pCurrent0, 54, 30, -12829636);
            this.font.draw(poseStack, pCurrent1, 54, 42, -12829636);
            this.font.draw(poseStack, pCurrent2, 54, 54, -12829636);
        }

        if (guiType < 2) {
            this.font.draw(poseStack, "Cube", 10, 51, -12829636);
        } else {
            this.font.draw(poseStack, "Scroll", 10, 51, -12829636);
        }
        this.font.draw(poseStack, "Equip", 10, 19, -12829636);

        this.font.draw(poseStack, "Reconfigure Equip Potentials", 19, 5, -12829636);
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
            MapleCraftMod.PACKET_HANDLER.sendToServer(new CubeGUIMenuButtonMessage(0, guiType));
            CubeGUIMenuButtonMessage.handleButtonAction(entity, 0, guiType);
        }) {
            @Override
            public void render(PoseStack poseStack, int gx, int gy, float ticks) {
                if (guiType == 1) {
                    this.x = CubeGUIMenuScreen.super.leftPos + 45;
                    this.y = CubeGUIMenuScreen.super.topPos + 58;
                } else {
                    this.x = CubeGUIMenuScreen.super.leftPos + 131;
                    this.y = CubeGUIMenuScreen.super.topPos + 34;
                }
                super.render(poseStack, gx, gy, ticks);
            }
        });

        this.addRenderableWidget(new Button(this.leftPos + 115, this.topPos + 58, 40, 20, Component.literal("APPLY"), e -> {
            if (guiType == 1) {
                MapleCraftMod.PACKET_HANDLER.sendToServer(new CubeGUIMenuButtonMessage(1, guiType));
                CubeGUIMenuButtonMessage.handleButtonAction(entity, 1, guiType);
            }
        }) {
            @Override
            public void render(PoseStack poseStack, int gx, int gy, float ticks) {
                if (guiType == 1)
                    super.render(poseStack, gx, gy, ticks);
            }
        });
    }

    public static void showPotentialText(ItemStack itemStack, boolean isEquip) {
        if (!isEquip) {
            CubeGUIMenuScreen.pCurrent0 = "";
            CubeGUIMenuScreen.pCurrent1 = "";
            CubeGUIMenuScreen.pCurrent2 = "";
        } else {
            EquipWiseData data = itemStack.getCapability(EQUIP_CAPABILITIES).orElse(new EquipWiseData());
            CubeGUIMenuScreen.pCurrent0 = data.potentials[0].toString();
            CubeGUIMenuScreen.pCurrent1 = data.potentials[1].toString();
            CubeGUIMenuScreen.pCurrent2 = data.potentials[2].toString();

            if (guiType == 1 && CubeItem.updated) {
                CubeGUIMenuScreen.pAfter0 = CubeItem.newPotentials[0].toString();
                CubeGUIMenuScreen.pAfter1 = CubeItem.newPotentials[1].toString();
                CubeGUIMenuScreen.pAfter2 = CubeItem.newPotentials[2].toString();
            }
        }
    }
}

