package net.maplecraft.items;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.MapleArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class RedHeadbandHatItem extends MapleArmorItem {
    public RedHeadbandHatItem() {
        super("hat_red_headband",
                50,
                EquipCategory.HELMET,
                new BonusStats(List.of("DEFENSE"), List.of(1)),
                () -> { return Ingredient.of(ItemsInit.ETC_MESO_TINY.get()); });
    }
}
