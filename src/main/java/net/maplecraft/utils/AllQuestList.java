package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class AllQuestList {
    public static List<QuestEntry> QUESTS  = List.of(
        new QuestEntry(10001, new ItemStack(ItemsInit.USE_RED_POTION.get(), 1), ItemStack.EMPTY, new ItemStack(ItemsInit.ETC_MESO_SMALL.get(), 1)),
        new QuestEntry(10002, new ItemStack(ItemsInit.USE_ORANGE_POTION.get(), 1), ItemStack.EMPTY, new ItemStack(ItemsInit.ETC_MESO_SMALL.get(), 1)),
        new QuestEntry(10003),
        new QuestEntry(10004),
        new QuestEntry(10005, new ItemStack(Items.GUNPOWDER, 10), ItemStack.EMPTY, ItemStack.EMPTY)
    );

    public static final int QUEST_COUNT = QUESTS.size();
    public static final String DEFAULT_STATE = "11111";
}
