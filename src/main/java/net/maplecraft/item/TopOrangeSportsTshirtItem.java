package net.maplecraft.item;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.MapleArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

public class TopOrangeSportsTshirtItem extends MapleArmorItem {
    public TopOrangeSportsTshirtItem() {
        super("top_orange_sports_tshirt",
                50,
                new EquipBaseData().category(EquipCategory.TOP)
                        .addStat("ARMOR", 2)
                        .addStat("SPEED", 3),
                () -> { return Ingredient.of(ItemsInit.ETC_MESO_TINY.get()); });
    }
}
