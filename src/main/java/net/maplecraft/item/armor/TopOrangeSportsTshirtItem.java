package net.maplecraft.item.armor;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.MapleArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

import static net.maplecraft.utils.AllArmorEquipKeyValues.TOP_ORANGE_SPORTS_TSHIRT_KV;

public class TopOrangeSportsTshirtItem extends MapleArmorItem {
    public static String itemName = "top_orange_sports_tshirt";
    public TopOrangeSportsTshirtItem() {
        super(itemName,
                new EquipBaseData().category(EquipCategory.TOP)
                        .levelReq(TOP_ORANGE_SPORTS_TSHIRT_KV.levelReq)
                        .addStat("ARMOR", TOP_ORANGE_SPORTS_TSHIRT_KV.armor)
                        .addStat("MAX HP", TOP_ORANGE_SPORTS_TSHIRT_KV.maxHP)
                        .addStat("STATS", TOP_ORANGE_SPORTS_TSHIRT_KV.stats)
                        .addStat("SPEED", TOP_ORANGE_SPORTS_TSHIRT_KV.speed)
                        .addStat("JUMP", TOP_ORANGE_SPORTS_TSHIRT_KV.jump),
                Ingredient.of(ItemsInit.ETC_BASIC_MONSTER_CRYSTAL.get()));
    }
}
