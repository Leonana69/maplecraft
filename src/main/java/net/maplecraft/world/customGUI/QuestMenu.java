package net.maplecraft.world.customGUI;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.init.MenusInit;
import net.maplecraft.network.CubeScreenSlotMessageHandler;
import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.IBaseEquip;
import net.maplecraft.utils.QuestEntry;
import net.maplecraft.utils.ScrollItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class QuestMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final Level world;
    public final Player entity;
    private final int customSlotCount = 2;
    private final IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    public final int maxQuestsWithoutScroll = 5;
    public List<QuestEntry> quests = new ArrayList<>();

    public QuestMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(MenusInit.QUEST_MENU.get(), id);
        this.entity = inv.player;
        this.world = inv.player.level;
        // two custom slots
        this.internal = new ItemStackHandler(customSlotCount);

        // custom slots
        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 8, 31) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() instanceof IBaseEquip;
            }
        }));
        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 8, 62) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() instanceof CubeItem || stack.getItem() instanceof ScrollItem;
            }
        }));

        // inventory
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 8 + si * 18, 142));
    }

    public boolean canScroll() {
        // TODO: scroll
        return true;
    }

    public void scrollTo(int index, float offset) {
        // TODO: scroll
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
