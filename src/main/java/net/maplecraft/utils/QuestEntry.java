package net.maplecraft.utils;

import net.maplecraft.network.Variables;
import net.maplecraft.world.customGUI.QuestMenu;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static net.maplecraft.world.customGUI.QuestMenu.customSlotCount;

public class QuestEntry {

    public enum QuestState {
        UNAVAILABLE(0),
        AVAILABLE(1),
        IN_PROGRESS(2),
        COMPLETED(3);

        public static final List<QuestState> VALUES = List.of(values());
        public final int type;
        QuestState(int type) {
            this.type = type;
        }
    }
    public ItemStack [] requests = new ItemStack[] {
            ItemStack.EMPTY,
            ItemStack.EMPTY,
    };
    public ItemStack reward = ItemStack.EMPTY;
    public int questID;
    public int prerequisite = -1;
    public int levelReq = 0;

    public QuestEntry(int questID) {
        this.questID = questID;
    }

    public QuestEntry(int questID, ItemStack request0, ItemStack request1, ItemStack reward) {
        this.questID = questID;
        this.requests[0] = request0;
        this.requests[1] = request1;
        this.reward = reward;
    }

    public QuestEntry(int questID, ItemStack reward) {
        this.questID = questID;
        this.reward = reward;
    }

    public boolean canComplete(QuestMenu menu) {
        int slotIndex0 = menu.findItem(this.requests[0]);
        int slotIndex1 = menu.findItem(this.requests[1]);
        return slotIndex0 >= 0 && slotIndex1 >= 0;
    }

    public boolean isAvailable(Player player) {
        String questState = (String) Variables.get(player, "questState");

        if (questState.charAt(getQuestIndexFromList(AllQuestList.QUESTS, this.questID)) - '0' == QuestState.UNAVAILABLE.type) {
            boolean flag1 = true;
            if (prerequisite > 10000) {
                int prerequisiteState = questState.charAt(getQuestIndexFromList(AllQuestList.QUESTS, this.prerequisite)) - '0';
                if (prerequisiteState != QuestState.COMPLETED.type) {
                    flag1 = false;
                }
            }
            return flag1 && player.experienceLevel >= levelReq;
        }
        return false;
    }

    public void onComplete(QuestMenu menu) {
        int slotIndex0 = menu.findItem(this.requests[0]);
        int slotIndex1 = menu.findItem(this.requests[1]);
        if (slotIndex0 >= customSlotCount) {
            Slot slot = menu.slots.get(slotIndex0);
            ItemStack itemStack = slot.getItem();
            itemStack.shrink(this.requests[0].getCount());
            slot.set(itemStack);
        }

        if (slotIndex1 >= customSlotCount) {
            Slot slot = menu.slots.get(slotIndex1);
            ItemStack itemStack = slot.getItem();
            itemStack.shrink(this.requests[1].getCount());
            slot.set(itemStack);
        }

        int rewardSlot = menu.findRewardSlot(this.reward);
        if (rewardSlot >= 0) {
            ItemStack itemStack = menu.slots.get(rewardSlot).getItem();
            if (itemStack.isEmpty()) {
                menu.slots.get(rewardSlot).set(this.reward.copy());
            } else {
                itemStack.setCount(itemStack.getCount() + this.reward.getCount());
                menu.slots.get(rewardSlot).set(itemStack);
            }
        } else {
            ItemEntity entityToSpawn = new ItemEntity(menu.world, menu.entity.getX(), menu.entity.getY(), menu.entity.getZ(),
                    this.reward.copy());
            entityToSpawn.setPickUpDelay(10);
            menu.world.addFreshEntity(entityToSpawn);
        }
    }

    public static QuestEntry getQuestFromList(List<QuestEntry> list, int questID) {
        for (QuestEntry questEntry : list) {
            if (questEntry.questID == questID)
                return questEntry;
        }
        return new QuestEntry(10000);
    }

    public static int getQuestIndexFromList(List<QuestEntry> list, int questID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).questID == questID)
                return i;
        }
        return -1;
    }

    public QuestEntry setPrerequisite(int i) {
        this.prerequisite = i;
        return this;
    }

    public QuestEntry setLevelReq(int i) {
        this.levelReq = i;
        return this;
    }
}
