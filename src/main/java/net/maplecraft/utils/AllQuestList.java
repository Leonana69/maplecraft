package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.network.Variables;
import net.maplecraft.world.customGUI.QuestMenu;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class AllQuestList {
    public static List<QuestEntry> QUESTS  = List.of(
        new QuestEntry(10001, new ItemStack(ItemsInit.USE_RED_POTION.get(), 1), ItemStack.EMPTY, new ItemStack(ItemsInit.ETC_MESO_SMALL.get(), 1)),
        new QuestEntry(10002, new ItemStack(ItemsInit.USE_ORANGE_POTION.get(), 1), ItemStack.EMPTY, new ItemStack(ItemsInit.ETC_MESO_SMALL.get(), 1)),
        new QuestEntry(10003, new ItemStack(ItemsInit.USE_EPIC_POTENTIAL_SCROLL.get())) {
            @Override
            public boolean canComplete(QuestMenu menu) {
                boolean flag = false;
                Player player = menu.entity;
                if (player.getMainHandItem().getItem() instanceof IBaseEquip baseEquip
                        && baseEquip.hasPotential(player.getMainHandItem()))
                    flag = true;
                return flag && super.canComplete(menu);
            }
        },
        new QuestEntry(10004),
        new QuestEntry(10005, new ItemStack(Items.GUNPOWDER, 10), ItemStack.EMPTY, new ItemStack(ItemsInit.USE_JOB_ADVANCEMENT_COIN.get())),
        new QuestEntry(10006, new ItemStack(Items.BLAZE_ROD, 10), ItemStack.EMPTY, new ItemStack(ItemsInit.USE_JOB_ADVANCEMENT_COIN.get()))
                .setPrerequisite(10005).setLevelReq(15),
        new QuestEntry(10007, new ItemStack(Items.ENDER_PEARL, 10), ItemStack.EMPTY, new ItemStack(ItemsInit.USE_JOB_ADVANCEMENT_COIN.get()))
                .setPrerequisite(10006).setLevelReq(30)
    );

    public static final int QUEST_COUNT = QUESTS.size();
    public static final String DEFAULT_STATE = "0000000";
}
