package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;

public class BaseEquipItem extends Item {
    public final int max_star_force = 5;

    public EquipCategory category;
    public BonusStats baseStats;
    public PotentialRarity potentialRarity;
    public List<PotentialType> potentialType;

    public int levels_req = 0;
    public int job_req = 0;
    public int star_force = 0;

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
        // star force
        char [] cur_star = new char[star_force];
        char [] empty_star = new char[max_star_force - star_force];
        Arrays.fill(cur_star, '★');
        Arrays.fill(empty_star, '☆');
        list.add(Component.literal(TextFormatter.format(new String(cur_star), ChatFormatting.YELLOW) +
                TextFormatter.format(new String(empty_star), ChatFormatting.WHITE)));

        // description
        list.add(Component.literal(description));

        // category
        list.add(Component.literal(
            Component.translatable("utils.maplecraft.base_equip_item_category").getString()
            + category.typeName));

        // baseStats
        for (int i = 0; i < baseStats.valueTypes; i++) {
            int value = baseStats.values[i];
            if (value > 0) {
                list.add(Component.literal(BonusStats.valuesName.get(i) + ": +" + value));
            }
        }

        // potential
        list.add(Component.translatable("utils.maplecraft.base_equip_item_divider"));
        list.add(Component.literal(TextFormatter.format(potentialRarity.typeName
            + Component.translatable("utils.maplecraft.base_equip_item_potential").getString(), potentialRarity.color)));
        if (potentialRarity == PotentialRarity.COMMON) {
            list.add(Component.translatable("utils.maplecraft.base_equip_item_potential_common"));
        } else {
            potentialType.forEach((pt) -> {
                if (pt != PotentialType.NONE) {
                    list.add(Component.literal(pt.toString()));
                }
            });
        }
    }
}
