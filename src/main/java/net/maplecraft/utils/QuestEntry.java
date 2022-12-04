package net.maplecraft.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class QuestEntry {
    public static enum QuestState {
        AVAILABLE(0),
        IN_PROGRESS(1),
        COMPLETED(2);

        public static final List<QuestState> VALUES = List.of(values());
        public final int type;
        QuestState(int type) {
            this.type = type;
        }
    }
    public List<ItemStack> requests = null;
    public List<ItemStack> rewards = null;
    public int questID;
    public int prerequisite;
    public int levelReq;

    public QuestState state = QuestState.AVAILABLE;

    public QuestEntry(int questID) {
        this.questID = questID;
    }
}
