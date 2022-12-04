package net.maplecraft.world.customGUI;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.init.MenusInit;
import net.maplecraft.utils.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

import static net.maplecraft.utils.AllQuestList.QUESTS;

public class QuestMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final Level world;
    public final Player entity;
    private final int customSlotCount = 2;
    private final IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    public final int maxQuestEntryWithoutScroll = 8;
    public final int maxQuestDescriptionWithoutScroll = 10;
    public List<Integer> availableQuests = Arrays.asList(10001, 10002, 10004, 10005, 10003);
    public List<Integer> inProgressQuests = Arrays.asList(10004, 10001);
    public List<Integer> completedQuests = new ArrayList<>();

    public int firstQuestIndex = 0;
    public int firstDescriptionLineIndex = 0;
    public QuestEntry.QuestState currentTab = QuestEntry.QuestState.AVAILABLE;
    public QuestEntry selectedQuest = null;
    public String [] selectedQuestTitle;
    public String [] selectedQuestDescription;

    public QuestMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(MenusInit.QUEST_MENU.get(), id);
        this.entity = inv.player;
        this.world = inv.player.level;
        // two custom slots
        this.internal = new ItemStackHandler(customSlotCount);

        // custom slots
        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 122, 117) {
            @Override
            public boolean isActive() {
                return selectedQuest != null;
            }
        }));
        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 142, 117) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
            @Override
            public boolean isActive() {
                return selectedQuest != null;
            }
            @Override
            public boolean mayPickup(Player playerIn) {
                return false;
            }
        }));

        // inventory
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 8 + si * 18, 142));
    }

    public boolean canScroll(int index) {
        if (index == 0) {
            return getQuestList().size() > maxQuestEntryWithoutScroll;
        } else {
            return selectedQuest != null
                    && selectedQuestDescription.length > maxQuestDescriptionWithoutScroll;
        }
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
            firstDescriptionLineIndex = Math.round((selectedQuestDescription.length - maxQuestDescriptionWithoutScroll) * offset);
        }
    }

    public void selectQuest(int index) {
        this.selectedQuest = QUESTS.get(getQuestList().get(index + this.firstQuestIndex));
        String title = Component.translatable("quest.maplecraft." + this.selectedQuest.questID + "_title").getString();
        String description = Component.translatable("quest.maplecraft." + this.selectedQuest.questID + "_description").getString();
        firstDescriptionLineIndex = 0;
        selectedQuestTitle = title.split("(?<=\\G.{" + 15 + "})");
        selectedQuestDescription = description.split("(?<=\\G.{" + 23 + "})");
        for (int i = 0; i < selectedQuestTitle.length; i++) {
            if (selectedQuestTitle[i].charAt(0) == ' ') {
                selectedQuestTitle[i] = selectedQuestTitle[i].substring(1);
            }
        }
        for (int i = 0; i < selectedQuestDescription.length; i++) {
            if (selectedQuestDescription[i].charAt(0) == ' ') {
                selectedQuestDescription[i] = selectedQuestDescription[i].substring(1);
            }
        }
        this.selectedQuest.rewards = new ItemStack(ItemsInit.ETC_ADVANCED_MONSTER_CRYSTAL.get(), 2);
        this.customSlots.get(1).set(this.selectedQuest.rewards);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (index < customSlotCount) {
                // move itemStack to inventory
                if (!this.moveItemStackTo(itemStack1, customSlotCount, this.slots.size(), true))
                    return ItemStack.EMPTY;
                slot.onQuickCraft(itemStack1, itemStack);
            } else if (!this.moveItemStackTo(itemStack1, 0, customSlotCount, false)) {
                // if you can not move itemStack to quest slots
                // just don't move
                return ItemStack.EMPTY;
            }
            if (itemStack1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
            if (itemStack1.getCount() == itemStack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(player, itemStack1);
        }
        return itemStack;
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
