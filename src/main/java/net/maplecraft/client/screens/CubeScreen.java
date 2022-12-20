package net.maplecraft.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.CubeScreenButtonMessageHandler;
import net.maplecraft.item.CubeItem;
import net.maplecraft.utils.EquipWiseData;
import net.maplecraft.utils.IBaseEquip;
import net.maplecraft.inventory.CubeMenu;
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
import software.bernie.geckolib3.geo.raw.pojo.Cube;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class CubeScreen extends AbstractContainerScreen<CubeMenu> {
    private final Player entity;
    public static String pCurrent0 = "";
    public static String pCurrent1 = "";
    public static String pCurrent2 = "";
    public static String pAfter0 = "";
    public static String pAfter1 = "";
    public static String pAfter2 = "";

    public CubeScreen(CubeMenu container, Inventory inventory, Component text) {
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

        if (this.menu.guiType == 1) {
            RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/screens/cube_potential_background_old.png"));
            GuiComponent.blit(poseStack, this.leftPos + 35, this.topPos + 19, 0, 0, 62, 36, 62, 36);
            RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/screens/cube_potential_background_new.png"));
            GuiComponent.blit(poseStack, this.leftPos + 102, this.topPos + 19, 0, 0, 62, 36, 62, 36);

        } else {
            RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/screens/cube_potential_background_old.png"));
            GuiComponent.blit(poseStack, this.leftPos + 48, this.topPos + 30, 0, 0, 62, 36, 62, 36);
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
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        if (this.menu.guiType == 1) {
            // black cube
            this.font.draw(poseStack, pCurrent0, 37, 22, -12829636);
            this.font.draw(poseStack, pCurrent1, 37, 34, -12829636);
            this.font.draw(poseStack, pCurrent2, 37, 46, -12829636);

            this.font.draw(poseStack, pAfter0, 104, 22, -12829636);
            this.font.draw(poseStack, pAfter1, 104, 34, -12829636);
            this.font.draw(poseStack, pAfter2, 104, 46, -12829636);
        } else {
            this.font.draw(poseStack, pCurrent0, 50, 33, -12829636);
            this.font.draw(poseStack, pCurrent1, 50, 45, -12829636);
            this.font.draw(poseStack, pCurrent2, 50, 57, -12829636);
        }

        if (this.menu.guiType < 2) {
            this.font.draw(poseStack, "Cube", 7, 51, -12829636);
        } else {
            this.font.draw(poseStack, "Scroll", 7, 51, -12829636);
        }
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
        this.addRenderableWidget(new Button(this.leftPos + 129, this.topPos + 37, 40, 20, Component.literal("USE"), e -> {
            MapleCraftMod.PACKET_HANDLER.sendToServer(new CubeScreenButtonMessageHandler(0, this.menu.guiType));
            CubeScreenButtonMessageHandler.handleButtonAction(entity, 0, this.menu.guiType);
        }) {
            @Override
            public void render(PoseStack poseStack, int gx, int gy, float ticks) {
                Player player = Minecraft.getInstance().player;
                if (player != null && player.containerMenu instanceof CubeMenu cubeMenu) {
                    if (cubeMenu.guiType == 1) {
                        this.x = CubeScreen.super.leftPos + 46;
                        this.y = CubeScreen.super.topPos + 58;
                    } else {
                        this.x = CubeScreen.super.leftPos + 129;
                        this.y = CubeScreen.super.topPos + 37;
                    }
                }
                super.render(poseStack, gx, gy, ticks);
            }
        });

        this.addRenderableWidget(new Button(this.leftPos + 113, this.topPos + 58, 40, 20, Component.literal("APPLY"), e -> {
            if (this.menu.guiType == 1) {
                MapleCraftMod.PACKET_HANDLER.sendToServer(new CubeScreenButtonMessageHandler(1, this.menu.guiType));
                CubeScreenButtonMessageHandler.handleButtonAction(entity, 1, this.menu.guiType);
            }
        }) {
            @Override
            public void render(PoseStack poseStack, int gx, int gy, float ticks) {
                Player player = Minecraft.getInstance().player;
                if (player != null && player.containerMenu instanceof CubeMenu cubeMenu) {
                    if (cubeMenu.guiType == 1) {
                        super.render(poseStack, gx, gy, ticks);
                    }
                }
            }
        });
    }

    public static void showPotentialText(CubeMenu cubeMenu, ItemStack itemStack) {
        if (itemStack.getItem() instanceof IBaseEquip baseEquip) {
            EquipWiseData data = baseEquip.getEquipWiseData(itemStack);
            CubeScreen.pCurrent0 = data.potentials[0].toString();
            CubeScreen.pCurrent1 = data.potentials[1].toString();
            CubeScreen.pCurrent2 = data.potentials[2].toString();
            CubeScreen.pAfter0 = data.potentialsNew[0].toString();
            CubeScreen.pAfter1 = data.potentialsNew[1].toString();
            CubeScreen.pAfter2 = data.potentialsNew[2].toString();
        } else {
            CubeScreen.pCurrent0 = "";
            CubeScreen.pCurrent1 = "";
            CubeScreen.pCurrent2 = "";
            CubeScreen.pAfter0 = "";
            CubeScreen.pAfter1 = "";
            CubeScreen.pAfter2 = "";
        }
    }
}

