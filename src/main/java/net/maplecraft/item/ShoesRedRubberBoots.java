package net.maplecraft.item;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.MapleArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

public class ShoesRedRubberBoots extends MapleArmorItem {
    public ShoesRedRubberBoots() {
        super("shoes_red_rubber_boots",
                50,
                new EquipBaseData().category(EquipCategory.SHOES)
                        .addStat("ARMOR", 2),
                () -> { return Ingredient.of(ItemsInit.ETC_MESO_TINY.get()); });
    }
}
