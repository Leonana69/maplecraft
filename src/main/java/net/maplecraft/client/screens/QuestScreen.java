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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class QuestScreen extends AbstractContainerScreen<QuestMenu> {
    private static final int SCROLLER_WIDTH = 5;
    private static final int SCROLLER_HEIGHT = 15;
    private float scrollOffs;
    private int scrolling;
    private final Player entity;

    private final int [] scrollBarStartX = new int [] { 7, 162 };
    private final int [] scrollBarStartY = new int [] { 7, 7 };
    private final int [] scrollBarHeight = new int [] { 128, 128 };


    public QuestScreen(QuestMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    private static final ResourceLocation texture = new ResourceLocation("maplecraft:textures/screens/quest_screen.png");

    private boolean canScroll(int index) {
        // TODO: right scroll
        if (index == 0)
            return this.menu.canScroll();
        else
            return true;
    }

    @Override
    public boolean mouseScrolled(double x, double y, double scroll) {
        // TODO: right scroll
        System.out.println("mouseScrolled: " + x + ", " + y);
        int index = -1;
        if (insideScrollbar(x, y, 0)) {
            index = 0;
        } else if (insideScrollbar(x, y, 1)) {
            index = 1;
        }

        if (index < 0 || !this.canScroll(index)) {
            return false;
        } else {
            int i = this.menu.quests.size() - this.menu.maxQuestsWithoutScroll;
            float f = (float)(scroll / (double)i);
            this.scrollOffs = Mth.clamp(this.scrollOffs + f, 0.0F, 1.0F);
            this.menu.scrollTo(index, this.scrollOffs);

            System.out.println("scroll "+index+" to: " + this.scrollOffs);
            return true;
        }
    }

    protected boolean insideScrollbar(double x, double y, int index) {
        int left = this.leftPos + scrollBarStartX[index];
        int top = this.topPos + scrollBarStartY[index];
        int right = left + SCROLLER_WIDTH + 1;
        int bottom = top + scrollBarHeight[index];
        return x > (double)left && y > (double)top && x < (double)right && y < (double)bottom;
    }

    @Override
    public boolean mouseClicked(double x, double y, int p_97750_) {
        // TODO: scroll
        if (p_97750_ == 0) {
            if (this.insideScrollbar(x, y, 0)) {
                System.out.println("click IN scroll bar 0");
                this.scrolling = this.canScroll(0) ? 0 : -1;
                return true;
            } else if (this.insideScrollbar(x, y, 1)) {
                System.out.println("click IN scroll bar 1");
                this.scrolling = this.canScroll(1) ? 1 : -1;
                return true;
            }
        }
        return super.mouseClicked(x, y, p_97750_);
    }

    @Override
    public boolean mouseReleased(double x, double y, int p_98624_) {
        if (p_98624_ == 0) {
            this.scrolling = -1;
        }
        return super.mouseReleased(x, y, p_98624_);
    }

    @Override
    public boolean mouseDragged(double x, double y, int p_98537_, double p_98538_, double p_98539_) {
        if (this.scrolling > -1) {
            int i = this.topPos + scrollBarStartY[this.scrolling];
            int j = i + scrollBarHeight[this.scrolling];
            this.scrollOffs = ((float)y - i - SCROLLER_HEIGHT / 2.0F) / ((float)(j - i) - SCROLLER_HEIGHT);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.menu.scrollTo(this.scrolling, this.scrollOffs);
            System.out.println("dragged " + this.scrolling + " scroll to: " + this.scrollOffs);
            return true;
        } else {
            return super.mouseDragged(x, y, p_98537_, p_98538_, p_98539_);
        }
    }

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
//        this.font.draw(poseStack, "Equip", 7, 19, -12829636);
//        this.font.draw(poseStack, "Reconfigure Equip Potentials", 16, 5, -12829636);
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
//        this.addRenderableWidget(new Button(this.leftPos + 128, this.topPos + 37, 40, 20, Component.literal("USE"), e -> {
////            MapleCraftMod.PACKET_HANDLER.sendToServer(new CubeScreenButtonMessageHandler(0, guiType));
////            CubeScreenButtonMessageHandler.handleButtonAction(entity, 0, guiType);
//        }) {
//            @Override
//            public void render(PoseStack poseStack, int gx, int gy, float ticks) {
//                super.render(poseStack, gx, gy, ticks);
//            }
//        });

    }
}

