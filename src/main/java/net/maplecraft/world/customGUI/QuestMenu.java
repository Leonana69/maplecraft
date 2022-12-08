package net.maplecraft.world.customGUI;

import net.maplecraft.init.MenusInit;
import net.maplecraft.network.Variables;
import net.maplecraft.utils.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

import static net.maplecraft.utils.AllQuestList.*;
import static net.maplecraft.utils.QuestEntry.QuestState.*;
import static net.maplecraft.utils.QuestEntry.getQuestFromList;
import static net.maplecraft.utils.QuestEntry.getQuestIndexFromList;

public class QuestMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final Level world;
    public final Player entity;
    public static final int customSlotCount = 3;
    private final IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    public final int maxQuestEntryWithoutScroll = 8;
    public final int maxQuestDescriptionWithoutScroll = 8;
    public List<Integer> availableQuests = new ArrayList<>();
    public List<Integer> inProgressQuests = new ArrayList<>();
    public List<Integer> completedQuests = new ArrayList<>();
    public QuestEntry.QuestState currentTab = AVAILABLE;
    public int firstQuestIndex = 0;
    public int firstDescriptionLineIndex = 0;
    public QuestEntry selectedQuest = null;
    public int selectedQuestIndex = -1;
    public List<String> selectedQuestTitle;
    public List<String> selectedQuestDescription;

    public QuestMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(MenusInit.QUEST_MENU.get(), id);
        this.entity = inv.player;
        this.world = inv.player.level;
        // custom slots
        this.internal = new ItemStackHandler(customSlotCount);

        // custom slots
        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 83, 98) {
            @Override
            public boolean isActive() {
                return selectedQuest != null;
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
            @Override
            public boolean mayPickup(Player playerIn) {
                return false;
            }
        }));
        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 101, 98) {
            @Override
            public boolean isActive() {
                return selectedQuest != null;
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
            @Override
            public boolean mayPickup(Player playerIn) {
                return false;
            }
        }));
        this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 142, 98) {
            @Override
            public boolean isActive() {
                return selectedQuest != null;
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
            @Override
            public boolean mayPickup(Player playerIn) {
                return false;
            }
        }));

        // inventory
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 8 + si * 18, 142));

        loadQuest();
    }

    public void loadQuest() {
        String str = (String) Variables.get(this.entity, "questState");

        if (str.length() < QUEST_COUNT) {
            str = str + DEFAULT_STATE.substring(str.length());
            Variables.set(this.entity, "questState", str);
        }
        byte [] questState = str.getBytes();
        availableQuests.clear();
        inProgressQuests.clear();
        completedQuests.clear();

        for (int i = 0; i < QUEST_COUNT; i++) {
            if (questState[i] - '0' == AVAILABLE.type) {
                availableQuests.add(QUESTS.get(i).questID);
            } else if (questState[i] - '0' == IN_PROGRESS.type) {
                inProgressQuests.add(QUESTS.get(i).questID);
            } else if (questState[i] - '0' == COMPLETED.type) {
                completedQuests.add(QUESTS.get(i).questID);
            }
        }
    }

    public static void updateQuest(Player player, int questID, QuestEntry.QuestState newState) {
        String str = (String) Variables.get(player, "questState");
        int index = getQuestIndexFromList(QUESTS, questID);
        str = str.substring(0, index) + newState.type + str.substring(index + 1);
        Variables.set(player, "questState", str);
    }

    public boolean canScroll(int index) {
        if (index == 0) {
            return getQuestList().size() > maxQuestEntryWithoutScroll;
        } else {
            return selectedQuest != null
                    && selectedQuestDescription.size() > maxQuestDescriptionWithoutScroll;
        }
    }

    public void updateEntry(QuestEntry.QuestState tab) {
        this.currentTab = tab;
        this.selectedQuestIndex = -1;
        this.selectedQuest = null;
        this.firstQuestIndex = 0;
    }

    public boolean interact(int questID, int tabID) {
        return interact(this, questID, tabID);
    }

    public static boolean interact(QuestMenu menu, int questID, int tabID) {
        if (tabID == AVAILABLE.type) {
            updateQuest(menu.entity, questID, IN_PROGRESS);
            menu.loadQuest();
            return true;
        } else if (tabID == QuestEntry.QuestState.IN_PROGRESS.type) {
            QuestEntry quest = getQuestFromList(QUESTS, questID);
            int slotIndex0 = menu.findItem(quest.requests[0]);
            int slotIndex1 = menu.findItem(quest.requests[1]);
            if (quest.questCanComplete(menu.entity) && slotIndex0 >= 0 && slotIndex1 >= 0) {
                if (!menu.world.isClientSide) {
                    if (slotIndex0 >= customSlotCount) {
                        Slot slot = menu.slots.get(slotIndex0);
                        ItemStack itemStack = slot.getItem();
                        itemStack.shrink(quest.requests[0].getCount());
                        slot.set(itemStack);
                    }

                    if (slotIndex1 >= customSlotCount) {
                        Slot slot = menu.slots.get(slotIndex1);
                        ItemStack itemStack = slot.getItem();
                        itemStack.shrink(quest.requests[1].getCount());
                        slot.set(itemStack);
                    }

                    int rewardSlot = menu.findRewardSlot(quest.reward);
                    if (rewardSlot >= 0) {
                        ItemStack itemStack = menu.slots.get(rewardSlot).getItem();
                        if (itemStack.isEmpty()) {
                            menu.slots.get(rewardSlot).set(quest.reward);
                        } else {
                            itemStack.setCount(itemStack.getCount() + quest.reward.getCount());
                            menu.slots.get(rewardSlot).set(itemStack);
                        }
                    }
                }
                quest.questComplete(menu.entity);

                updateQuest(menu.entity, questID, COMPLETED);
                menu.loadQuest();
                return true;
            }
        }
        return false;
    }

    public int findItem(ItemStack request) {
        if (!request.isEmpty()) {
            for (int i = customSlotCount; i < this.slots.size(); i++) {
                ItemStack find = this.slots.get(i).getItem();
                if (!find.isEmpty() && find.getItem() == request.getItem() && find.getCount() >= request.getCount()) {
                    return i;
                }
            }
        } else
            return 0;
        return -1;
    }

    public int findRewardSlot(ItemStack itemStack) {
        for (int i = customSlotCount; i < this.slots.size(); i++) {
            ItemStack is = this.slots.get(i).getItem();
            if (is.isEmpty()
                    || (is.getItem() == itemStack.getItem()
                            && is.getCount() + itemStack.getCount() < is.getItem().getMaxStackSize(is)))
                return i;
        }
        return -1;
    }

    public List<Integer> getQuestList() {
        List<Integer> list = null;
        switch (currentTab) {
            case AVAILABLE -> list = availableQuests;
            case IN_PROGRESS -> list = inProgressQuests;
            case COMPLETED -> list = completedQuests;
        }
        return list;
    }

    public void scrollTo(int index, float offset) {
        if (index == 0) {
            firstQuestIndex = Math.round((getQuestList().size() - maxQuestEntryWithoutScroll) * offset);
        } else {
            firstDescriptionLineIndex = Math.round((selectedQuestDescription.size() - maxQuestDescriptionWithoutScroll) * offset);
        }
    }

    public void selectQuest(int index) {
        selectedQuestIndex = index + this.firstQuestIndex;
        this.selectedQuest = getQuestFromList(QUESTS, getQuestList().get(selectedQuestIndex));
        String title = Component.translatable("quest.maplecraft." + this.selectedQuest.questID + "_title").getString();
        String description = Component.translatable("quest.maplecraft." + this.selectedQuest.questID + "_description").getString();
        firstDescriptionLineIndex = 0;
        selectedQuestTitle = new ArrayList<>(Arrays.asList(title.split("(?<=\\G.{" + 15 + "})")));
        selectedQuestDescription = new ArrayList<>(Arrays.asList(description.split("(?<=\\G.{" + 21 + "})")));


        String req = "Req: ";
        if (!this.selectedQuest.requests[0].isEmpty()) {
            req += this.selectedQuest.requests[0].getHoverName().getString();
        }
        if (!this.selectedQuest.requests[1].isEmpty()) {
            req += ", " + this.selectedQuest.requests[1].getHoverName().getString();
        }
        String get = "Get: ";
        if (!this.selectedQuest.reward.isEmpty()) {
            get += this.selectedQuest.reward.getHoverName().getString();
        }
        selectedQuestDescription.addAll(Arrays.asList(req.split("(?<=\\G.{" + 21 + "})")));
        selectedQuestDescription.addAll(Arrays.asList(get.split("(?<=\\G.{" + 21 + "})")));

        for (int i = 0; i < selectedQuestTitle.size(); i++) {
            if (selectedQuestTitle.get(i).charAt(0) == ' ') {
                selectedQuestTitle.set(i, selectedQuestTitle.get(i).substring(1));
            }
        }

        for (int i = 0; i < selectedQuestDescription.size(); i++) {
            if (selectedQuestDescription.get(i).charAt(0) == ' ') {
                selectedQuestDescription.set(i, selectedQuestDescription.get(i).substring(1));
            }
        }

        this.customSlots.get(0).set(this.selectedQuest.requests[0]);
        this.customSlots.get(1).set(this.selectedQuest.requests[1]);
        this.customSlots.get(2).set(this.selectedQuest.reward);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        return ItemStack.EMPTY;
    }
    @Override
    protected boolean moveItemStackTo(ItemStack itemStack, int minSlotIndex, int maxSlotIndex, boolean fromEnd) {
        boolean flag = false;
        int i = fromEnd ? maxSlotIndex - 1 : minSlotIndex;
        if (itemStack.isStackable()) {
            while (!itemStack.isEmpty()) {
                if (i < minSlotIndex || i >= maxSlotIndex)
                    break;
                Slot slot = this.slots.get(i);
                ItemStack itemStackOnSlot = slot.getItem();
                if (slot.mayPlace(itemStackOnSlot) && !itemStackOnSlot.isEmpty() && ItemStack.isSameItemSameTags(itemStack, itemStackOnSlot)) {
                    int j = itemStackOnSlot.getCount() + itemStack.getCount();
                    int maxSize = Math.min(slot.getMaxStackSize(), itemStack.getMaxStackSize());
                    if (j <= maxSize) {
                        itemStack.setCount(0);
                        itemStackOnSlot.setCount(j);
                        slot.set(itemStackOnSlot);
                        flag = true;
                    } else if (itemStackOnSlot.getCount() < maxSize) {
                        itemStack.shrink(maxSize - itemStackOnSlot.getCount());
                        itemStackOnSlot.setCount(maxSize);
                        slot.set(itemStackOnSlot);
                        flag = true;
                    }
                }
                i += fromEnd ? -1 : 1;
            }
        }
        if (!itemStack.isEmpty()) {
            i = fromEnd ? maxSlotIndex - 1 : minSlotIndex;
            while (true) {
                if (i < minSlotIndex || i >= maxSlotIndex)
                    break;
                Slot slot1 = this.slots.get(i);
                ItemStack itemStack1 = slot1.getItem();
                if (itemStack1.isEmpty() && slot1.mayPlace(itemStack)) {
                    if (itemStack.getCount() > slot1.getMaxStackSize()) {
                        slot1.set(itemStack.split(slot1.getMaxStackSize()));
                    } else {
                        slot1.set(itemStack.split(itemStack.getCount()));
                    }
                    slot1.setChanged();
                    flag = true;
                    break;
                }
                i += fromEnd ? -1 : 1;
            }
        }
        return flag;
    }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);
        boolean bound = false;
        if (!bound && player instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getSlots(); ++j) {
                    player.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
                }
            } else {
                for (int i = 0; i < internal.getSlots(); ++i) {
                    player.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
                }
            }
        }
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }
}
