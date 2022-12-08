package net.maplecraft.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.List;

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
    public int prerequisite;
    public int levelReq;

    public QuestState state = QuestState.AVAILABLE;

    public QuestEntry(int questID) {
        this.questID = questID;
    }

    public QuestEntry(int questID, ItemStack request0, ItemStack request1, ItemStack reward) {
        this.questID = questID;
        this.requests[0] = request0;
        this.requests[1] = request1;
        this.reward = reward;
    }

    public boolean questCanComplete(Player player) {
        return true;
    }

    public void questComplete(Player player) {}

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
}
