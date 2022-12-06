package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.maplecraft.utils.AllSkillKeyValues.POWER_STRIKE;

public class AllQuestList {
    public static List<QuestEntry> QUESTS  = List.of(
        new QuestEntry(10001, new ItemStack(Items.GUNPOWDER, 10), ItemStack.EMPTY, new ItemStack(ItemsInit.USE_RARE_POTENTIAL_SCROLL.get(), 2)),
        new QuestEntry(10002),
        new QuestEntry(10003),
        new QuestEntry(10004),
        new QuestEntry(10005)
    );

    public static final int QUEST_COUNT = QUESTS.size();
    public static final String DEFAULT_STATE = "11111";
}
