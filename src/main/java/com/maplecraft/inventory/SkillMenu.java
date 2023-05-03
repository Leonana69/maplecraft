package com.maplecraft.inventory;

import com.maplecraft.init.MenusInit;
import com.maplecraft.network.SkillScreenSlotMessageHandler;
import com.maplecraft.network.Variables;
import com.maplecraft.utils.AllSkillList;
import com.maplecraft.utils.JobCategory;
import com.maplecraft.item.SkillItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SkillMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public static final String TITLE = "container.maplecraft.skill_menu_title";
    public final Level world;
    public final Player player;

    private final IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    public static SkillMenu getClientMenu(int id, Inventory inv) {
        return new SkillMenu(id, inv);
    }

    public static MenuProvider getServerMenu() {
        return new SimpleMenuProvider((id, inv, serverPlayer) -> new SkillMenu(id, inv), Component.translatable(TITLE));
    }

    protected SkillMenu(int id, Inventory inv) {
        super(MenusInit.SKILL_MENU.get(), id);
        this.player = inv.player;
        this.world = inv.player.level;
        int customSlotCount = 10;
        this.internal = new ItemStackHandler(customSlotCount);

        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 7, 117) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(0);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() instanceof SkillItem && !hasSameSkill(stack);
            }
        }));
        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 25, 117) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(1);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() instanceof SkillItem && !hasSameSkill(stack);
            }
        }));
        this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 43, 117) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(2);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() instanceof SkillItem && !hasSameSkill(stack);
            }
        }));
        this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 61, 117) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(3);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() instanceof SkillItem && !hasSameSkill(stack);
            }
        }));
        this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 25, 20) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(4);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        }));
        this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 43, 20) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(5);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        }));
        this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 25, 51) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(6);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        }));
        this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 43, 51) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(7);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        }));
        this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 25, 82) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(8);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        }));
        this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 43, 82) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(9);
            }
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        }));

        setSkill();
    }

    private void setSkill() {
        // I    slot 4, 5
        // II   slot 6, 7
        // III  slot 8, 9
        // Active slot 0, 1, 2, 3
        int type = (int) Variables.get(this.player, "jobType");

        for (int i = 0; i < 4; i++) {
            int skillID = (int) Variables.get(this.player, "skillID" + (i + 1));
            if (skillID > 0)
                this.customSlots.get(i).set(new ItemStack(AllSkillList.SKILLS.get(skillID)));
        }

        JobCategory job = JobCategory.getJob(type);
        for (int i = 1; i <= job.advancement; i++) {
            List<Integer> list = job.getSkillList(i);
            for (int j = 0; j < list.size(); j++) {
                int slot = 2 + i * 2 + j;
                this.customSlots.get(slot).set(new ItemStack(AllSkillList.SKILLS.get(list.get(j))));
            }
        }
    }

    private boolean hasSameSkill(ItemStack itemStack) {
        for (int i = 0; i < 4; i++) {
            ItemStack customStack = this.customSlots.get(i).getItem();
            if (customStack.getItem() instanceof SkillItem skillOther && itemStack.getItem() instanceof SkillItem skill) {
                return skillOther == skill;
            }
        }
        return false;
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
    public void removed(@NotNull Player player) {
        super.removed(player);
    }

    private void slotChanged(int slotId) {
        if (this.world != null) {
            SkillScreenSlotMessageHandler.handleSlotAction(player, slotId);
        }
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }
}
