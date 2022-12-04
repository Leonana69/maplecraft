package net.maplecraft.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.CubeScreenButtonMessageHandler;
import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.EquipWiseData;
import net.maplecraft.utils.IBaseEquip;
import net.maplecraft.utils.QuestEntry;
import net.maplecraft.world.customGUI.CubeMenu;
import net.maplecraft.world.customGUI.QuestMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static net.maplecraft.utils.AllQuestList.QUESTS;
import static net.maplecraft.utils.QuestEntry.QuestState.VALUES;

public class QuestScreen extends AbstractContainerScreen<QuestMenu> {
    private static final int TAB_WIDTH = 28;
    private static final int TAB_HEIGHT = 32;
    private static final int SCROLLER_WIDTH = 5;
    private static final int SCROLLER_HEIGHT = 15;

    private static final int ENTRY_WIDTH = 70;
    private static final int ENTRY_HEIGHT = 15;
    private final float [] scrollOffs = new float [] { 0, 0 };
    private int scrolling;
    private final Player entity;

    private final int [] scrollBarStartX = new int [] { 8, 163 };
    private final int [] scrollBarStartY = new int [] { 8, 8 };
    private final int scrollBarHeight = 128;

    private final int [] tabStartY = new int [] { -28, -26 };
    private int questEntryCount;
    private List<QuestEntryButton> entryButtonList;


    public QuestScreen(QuestMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    private static final ResourceLocation backgroundTexture = new ResourceLocation("maplecraft:textures/screens/quest_screen_background.png");
    private static final ResourceLocation scrollerTexture = new ResourceLocation("maplecraft:textures/screens/quest_screen_scroller.png");
    private static final ResourceLocation tabTexture = new ResourceLocation("maplecraft:textures/screens/quest_screen_tab.png");

    private boolean canScroll(int index) {
        return this.menu.canScroll(index);
    }

    @Override
    public boolean mouseScrolled(double x, double y, double scroll) {
        int index = -1;
        if (insideScrollbar(x, y, 0)) {
            index = 0;
        } else if (insideScrollbar(x, y, 1)) {
            index = 1;
        }

        if (index < 0 || !this.canScroll(index)) {
            return false;
        } else {
            int i = this.menu.getQuestList().size() - this.menu.maxQuestsWithoutScroll;
            float f = (float)(scroll / (double)i);
            this.scrollOffs[index] = Mth.clamp(this.scrollOffs[index] - f, 0.0F, 1.0F);
            this.menu.scrollTo(index, this.scrollOffs[index]);
            return true;
        }
    }

    protected boolean insideScrollbar(double x, double y, int index) {
        int left = this.leftPos + scrollBarStartX[index] - 1;
        int top = this.topPos + scrollBarStartY[index] - 1;
        int right = left + SCROLLER_WIDTH + 2;
        int bottom = top + scrollBarHeight;
        return x > (double)left && y > (double)top && x < (double)right && y < (double)bottom;
    }

    protected boolean insideTab(double x, double y, QuestEntry.QuestState tab) {
        int left = this.leftPos + (TAB_WIDTH + 1) * tab.type - 1;
        int top = this.topPos + tabStartY[1] - 1;
        int right = left + TAB_WIDTH + 1;
        int bottom = top + TAB_HEIGHT - 4;
        return x > (double)left && y > (double)top && x < (double)right && y < (double)bottom;
    }

    @Override
    public boolean mouseClicked(double x, double y, int p_97750_) {
        if (p_97750_ == 0) {
            if (this.insideScrollbar(x, y, 0)) {
                this.scrolling = this.canScroll(0) ? 0 : -1;
                return true;
            } else if (this.insideScrollbar(x, y, 1)) {
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
            for (int i = 0; i < 3; i++) {
                QuestEntry.QuestState tab = VALUES.get(i);
                if (insideTab(x, y, tab)) {
                    if (tab != this.menu.currentTab) {
                        this.menu.currentTab = tab;
                        updateEntryButtons();
                        return true;
                    }
                }
            }
        }
        return super.mouseReleased(x, y, p_98624_);
    }

    @Override
    public boolean mouseDragged(double x, double y, int p_98537_, double p_98538_, double p_98539_) {
        if (this.scrolling > -1) {
            int index = this.scrolling;
            int i = this.topPos + scrollBarStartY[index];
            int j = i + scrollBarHeight;
            this.scrollOffs[index] = ((float)y - i - SCROLLER_HEIGHT / 2.0F) / ((float)(j - i) - SCROLLER_HEIGHT);
            this.scrollOffs[index] = Mth.clamp(this.scrollOffs[index], 0.0F, 1.0F);
            this.menu.scrollTo(index, this.scrollOffs[index]);
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
        RenderSystem.setShaderTexture(0, backgroundTexture);
        GuiComponent.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.setShaderTexture(0, scrollerTexture);
        int length = scrollBarHeight - SCROLLER_HEIGHT;
        GuiComponent.blit(poseStack,
                this.leftPos + scrollBarStartX[0],
                this.topPos + scrollBarStartY[0] + (int)(scrollOffs[0] * length),
                0, 0, SCROLLER_WIDTH, SCROLLER_HEIGHT, SCROLLER_WIDTH, SCROLLER_HEIGHT);
        GuiComponent.blit(poseStack,
                this.leftPos + scrollBarStartX[1],
                this.topPos + scrollBarStartY[1] + (int)(scrollOffs[1] * length),
                0, 0, SCROLLER_WIDTH, SCROLLER_HEIGHT, SCROLLER_WIDTH, SCROLLER_HEIGHT);

        RenderSystem.setShaderTexture(0, tabTexture);

        for (int i = 0; i < 3; i++) {
            int y = 0, height = 0;
            float u;
            if (i == this.menu.currentTab.type) {
                y = tabStartY[0];
                height = TAB_HEIGHT;
                u = 0;
            } else {
                y = tabStartY[1];
                height = TAB_HEIGHT - 5;
                u = TAB_WIDTH;
            }

            if (i > 0)
                height -= 1;
            GuiComponent.blit(poseStack,
                    this.leftPos + (TAB_WIDTH + 1) * i,
                    this.topPos + y,
                    u, 0, TAB_WIDTH, height, TAB_WIDTH * 2, TAB_HEIGHT);
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
        this.font.draw(poseStack, "?", 11, -15, 0xff3c3c3c);
        this.font.draw(poseStack, "✰", 11 + TAB_WIDTH, -15, 0xff3c3c3c);
        this.font.draw(poseStack, "☒", 11 + TAB_WIDTH * 2, -15, 0xff3c3c3c);

        // quest list
        List<Integer> questList = this.menu.getQuestList();
        if (questList != null) {
            Collections.sort(questList);
            for (int i = 0; i < questEntryCount; i++) {
                int questID = QUESTS.get(questList.get(i + this.menu.firstQuestIndex)).questID;
                this.font.draw(poseStack, String.valueOf(questID), 18, 11 + i * 16, 0xff3c3c3c);
            }
        }

        // quest content
        if (this.menu.selectedQuest > -1) {
            QuestEntry entry = QUESTS.get(this.menu.getQuestList().get(this.menu.selectedQuest));
            String title = Component.translatable("quest.maplecraft." + entry.questID + "_title").getString();
            String description = Component.translatable("quest.maplecraft." + entry.questID + "_description").getString();

            this.font.draw(poseStack, title, 82, 11, 0xff3c3c3c);
            this.font.draw(poseStack, description, 82, 11, 0xff3c3c3c);

        }

    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    private void updateEntryButtons() {
        this.scrolling = -1;
        this.menu.selectedQuest = -1;
        this.scrollOffs[0] = this.scrollOffs[1] = 0;
        this.menu.firstQuestIndex = 0;
        this.clearWidgets();
        questEntryCount = Math.min(this.menu.getQuestList().size(), this.menu.maxQuestsWithoutScroll);
        for (int i = 0 ; i < questEntryCount; i++) {
            this.addRenderableWidget(entryButtonList.get(i));
        }
    }

    @Override
    public void init() {
        super.init();
        assert this.minecraft != null;
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        entryButtonList = List.of(
                new QuestEntryButton(this.leftPos + 15, this.topPos + 8, e -> {
                    this.menu.selectQuest(0);
                }),
                new QuestEntryButton(this.leftPos + 15, this.topPos + 8 + QuestEntryButton.textureHeight / 2, e -> {
                    this.menu.selectQuest(1);
                }),
                new QuestEntryButton(this.leftPos + 15, this.topPos + 8 + 2 * QuestEntryButton.textureHeight / 2, e -> {
                    this.menu.selectQuest(2);
                }),
                new QuestEntryButton(this.leftPos + 15, this.topPos + 8 + 3 * QuestEntryButton.textureHeight / 2, e -> {
                    this.menu.selectQuest(3);
                }),
                new QuestEntryButton(this.leftPos + 15, this.topPos + 8 + 4 * QuestEntryButton.textureHeight / 2, e -> {
                    this.menu.selectQuest(4);
                }),
                new QuestEntryButton(this.leftPos + 15, this.topPos + 8 + 5 * QuestEntryButton.textureHeight / 2, e -> {
                    this.menu.selectQuest(5);
                }),
                new QuestEntryButton(this.leftPos + 15, this.topPos + 8 + 6 * QuestEntryButton.textureHeight / 2, e -> {
                    this.menu.selectQuest(6);
                }),
                new QuestEntryButton(this.leftPos + 15, this.topPos + 8 + 7 * QuestEntryButton.textureHeight / 2, e -> {
                    this.menu.selectQuest(7);
                })
        );
        updateEntryButtons();
    }



    class QuestEntryButton extends ImageButton {
        private static final int textureWidth = 63;
        private static final int textureHeight = 32;

        private static final ResourceLocation texture = new ResourceLocation(MapleCraftMod.MODID, "textures/screens/quest_entry.png");

        public QuestEntryButton(int x, int y, OnPress callBack) {
            super(x, y, 63, 16, 0, 0, textureHeight / 2, texture, textureWidth, textureHeight, callBack);
        }
    }
}

