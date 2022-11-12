package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

public class BaseEquipItem extends Item {
    public EquipCategory category;
    public BonusStats baseStats;
    public PotentialRarity potentialRarity;
    public List<PotentialType> potentialType;

    public int levels_req = 0;
    public int job_req = 0;

    public String description;

    public BaseEquipItem(Properties itemProperties, EquipCategory ec, BonusStats bs) {
        super(itemProperties.tab(TabsInit.TAB_MAPLE_CRAFT));
        category = ec;
        baseStats = bs;
        potentialRarity = PotentialRarity.COMMON;
        potentialType = Arrays.asList(
                PotentialType.NONE,
                PotentialType.NONE,
                PotentialType.NONE);
        description = "Default information";
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal(description));
        // category
        list.add(Component.literal("CATEGORY: " + category.typeName));
        // baseStats
        for (int i = 0; i < baseStats.values.size(); i++) {
            int value = baseStats.values.get(i);
            if (value > 0) {
                list.add(Component.literal(BonusStats.valuesName.get(i) + ": +" + value));
            }
        }

        // potential
        list.add(Component.literal(TextFormatter.format(potentialRarity.typeName + " Potential", potentialRarity.color)));
        if (potentialRarity == PotentialRarity.COMMON) {
            list.add(Component.literal("No Bonus Stats"));
        } else {
            potentialType.forEach((pt) -> {
                if (pt != PotentialType.NONE) {
                    list.add(Component.literal(potentialType.toString()));
                }
            });
        }
    }
}
