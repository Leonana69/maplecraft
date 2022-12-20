package net.maplecraft.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.QuestScreenButtonMessageHandler;
import net.maplecraft.utils.*;
import net.maplecraft.inventory.QuestMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.maplecraft.client.screens.QuestScreen.QuestEntryButton.getNewButton;
import static net.maplecraft.client.screens.QuestScreen.QuestEntryButton.questEntryHeight;
import static net.maplecraft.utils.AllQuestList.QUESTS;
import static net.maplecraft.utils.QuestEntry.QuestState.*;
import static net.maplecraft.utils.QuestEntry.getQuestFromList;

public class QuestScreen extends AbstractContainerScreen<QuestMenu> {
    private static final int TAB_WIDTH = 28;
    private static final int TAB_HEIGHT = 32;
    private static final int SCROLLER_WIDTH = 5;
    private static final int SCROLLER_HEIGHT = 15;
    private int scrolling;
    private final float [] scrollOffs = new float [] { 0, 0 };
    private final int [] scrollBarStartX = new int [] { 8, 163 };
    private final int [] scrollBarStartY = new int [] { 8, 8 };
    private final int scrollBarHeight = 128;

    private final int [] tabStartY = new int [] { -28, -26 };
    private int questEntryCount;
    private List<QuestEntryButton> entryButtonList;


    public QuestScreen(QuestMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    private static final ResourceLocation backgroundTexture = new ResourceLocation("maplecraft:textures/screens/quest_screen_background.png");
    private static final ResourceLocation scrollerTexture = new ResourceLocation("maplecraft:textures/screens/quest_screen_scroller.png");
    private static final ResourceLocation tabTexture = new ResourceLocation("maplecraft:textures/screens/quest_screen_tab.png");
    private static final ResourceLocation slotTexture = new ResourceLocation("maplecraft:textures/screens/quest_screen_slot.png");

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
            int i = this.menu.getQuestList().size() - this.menu.maxQuestEntryWithoutScroll;
            float f = (float)(scroll / (double)i);
            this.scrollOffs[index] = Mth.clamp(this.scrollOffs[index] + f, 0.0F, 1.0F);
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
        int left = this.leftPos + (TAB_WIDTH + 1) * (tab.type - 1) - 1;
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
            // no UNAVAILABLE tab
            for (int i = 1; i < 4; i++) {
                QuestEntry.QuestState tab = VALUES.get(i);
                if (insideTab(x, y, tab)) {
                    if (tab != this.menu.currentTab) {
                        updateEntryButtons(tab);
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

        for (int i = 1; i < 4; i++) {
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

            if (i > 1)
                height -= 1;
            GuiComponent.blit(poseStack,
                    this.leftPos + (TAB_WIDTH + 1) * (i - 1),
                    this.topPos + y,
                    u, 0, TAB_WIDTH, height, TAB_WIDTH * 2, TAB_HEIGHT);
        }

        if (this.menu.selectedQuest != null) {
            RenderSystem.setShaderTexture(0, slotTexture);
            GuiComponent.blit(poseStack,
                    this.leftPos + 82,
                    this.topPos + 97,
                    0, 0, 18, 18, 18, 18);
            GuiComponent.blit(poseStack,
                    this.leftPos + 100,
                    this.topPos + 97,
                    0, 0, 18, 18, 18, 18);
            GuiComponent.blit(poseStack,
                    this.leftPos + 141,
                    this.topPos + 97,
                    0, 0, 18, 18, 18, 18);
            if (this.menu.currentTab != COMPLETED) {
                this.addRenderableWidget(getNewButton(this.leftPos + 82, this.topPos + 118, e -> {
                    int questID = this.menu.selectedQuest.questID;
                    int tabID = this.menu.currentTab.type;
                    if (this.menu.interact(questID, tabID)) {
                        updateEntryButtons(this.menu.currentTab);
                    }
                    MapleCraftMod.PACKET_HANDLER.sendToServer(new QuestScreenButtonMessageHandler(questID, tabID));
                }, 1));
            }
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
        this.font.draw(poseStack, "?", 11, -15, 0xff3c3c3c);
        this.font.draw(poseStack, "✰", 11 + TAB_WIDTH, -15, 0xff3c3c3c);
        this.font.draw(poseStack, "☒", 11 + TAB_WIDTH * 2, -15, 0xff3c3c3c);

        // quest list
        List<Integer> questList = this.menu.getQuestList();
        if (questList != null) {
            questList.sort(null);
            for (int i = 0; i < questEntryCount; i++) {
                int questID = getQuestFromList(QUESTS, questList.get(i + this.menu.firstQuestIndex)).questID;
                String title = Component.translatable("quest.maplecraft." + questID + "_title").getString();
                if (title.length() > 14) {
                    title = title.substring(0, 12) + "...";
                }
                poseStack.pushPose();
                float titleScale = 0.7F;
                poseStack.scale(titleScale, titleScale, titleScale);
                this.font.draw(poseStack, title, 18 / titleScale, (11 + i * questEntryHeight) / titleScale, 0xff3c3c3c);
                poseStack.popPose();
            }
        }

        // quest content
        if (this.menu.selectedQuest != null) {
            final float scale = 0.7F;
            float posY = 10;
            float deltaY = 10;
            for (int i = 0; i < this.menu.selectedQuestTitle.size(); i++) {
                this.font.draw(poseStack, this.menu.selectedQuestTitle.get(i), 82, posY, 0xff3c3c3c);
                posY += deltaY;
            }
            this.font.draw(poseStack, "-------------", 82, posY - 2, 0xff3c3c3c);
            this.font.draw(poseStack, "-------------", 82, 90, 0xff3c3c3c);
            String translate = null;
            switch (this.menu.currentTab) {
                case AVAILABLE -> translate = "quest.maplecraft.button_accept";
                case IN_PROGRESS -> translate = "quest.maplecraft.button_complete";
            }
            if (translate != null)
                this.font.draw(poseStack, Component.translatable(translate), 86, 122, 0xff3c3c3c);
            poseStack.pushPose();
            poseStack.scale(scale, scale, scale);
            posY = posY / scale + 7;
            int length = Math.min(this.menu.maxQuestDescriptionWithoutScroll, this.menu.selectedQuestDescription.size());
            for (int i = 0; i < length; i++) {
                this.font.draw(poseStack,
                        this.menu.selectedQuestDescription.get(i + this.menu.firstDescriptionLineIndex),
                        82 / scale, posY, 0xff3c3c3c);
                posY += deltaY;
            }
            poseStack.popPose();
        }
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    private void updateEntryButtons(QuestEntry.QuestState tab) {
        this.menu.updateEntry(tab);
        this.scrolling = -1;
        this.scrollOffs[0] = this.scrollOffs[1] = 0;
        this.clearWidgets();
        questEntryCount = Math.min(this.menu.getQuestList().size(), this.menu.maxQuestEntryWithoutScroll);
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
                getNewButton(this.leftPos + 15, this.topPos + 8, e -> {
                    this.menu.selectQuest(0);
                }, 0),
                getNewButton(this.leftPos + 15, this.topPos + 8 + QuestEntryButton.textureHeight / 2, e -> this.menu.selectQuest(1), 0),
                getNewButton(this.leftPos + 15, this.topPos + 8 + 2 * QuestEntryButton.textureHeight / 2, e -> this.menu.selectQuest(2), 0),
                getNewButton(this.leftPos + 15, this.topPos + 8 + 3 * QuestEntryButton.textureHeight / 2, e -> this.menu.selectQuest(3), 0),
                getNewButton(this.leftPos + 15, this.topPos + 8 + 4 * QuestEntryButton.textureHeight / 2, e -> this.menu.selectQuest(4), 0),
                getNewButton(this.leftPos + 15, this.topPos + 8 + 5 * QuestEntryButton.textureHeight / 2, e -> this.menu.selectQuest(5), 0),
                getNewButton(this.leftPos + 15, this.topPos + 8 + 6 * QuestEntryButton.textureHeight / 2, e -> this.menu.selectQuest(6), 0),
                getNewButton(this.leftPos + 15, this.topPos + 8 + 7 * QuestEntryButton.textureHeight / 2, e -> this.menu.selectQuest(7), 0)
        );
        updateEntryButtons(AVAILABLE);
    }

    static class QuestEntryButton extends ImageButton {
        private static final int textureWidth = 99;
        private static final int textureHeight = 32;
        public static final int questEntryWidth = 63;
        public static final int questEntryHeight = 16;
        private static final int buttonWidth = 36;
        private static final int buttonHeight = 16;
        private static final ResourceLocation texture = new ResourceLocation(MapleCraftMod.MODID, "textures/screens/quest_entry.png");

        public static QuestEntryButton getNewButton(int x, int y, OnPress callBack, int type) {
            if (type == 0)
                return new QuestEntryButton(x, y, questEntryWidth, questEntryHeight, 0, 0, callBack);
            else
                return new QuestEntryButton(x, y, buttonWidth, buttonHeight, questEntryWidth, 0, callBack);
        }

        public QuestEntryButton(int x, int y, int width, int height, int textureOffsetX, int textureOffsetY, OnPress callBack) {
            super(x, y, width, height, textureOffsetX, textureOffsetY, textureHeight / 2, texture, textureWidth, textureHeight, callBack);
        }
    }
}
