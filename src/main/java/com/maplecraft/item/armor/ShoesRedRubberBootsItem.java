package com.maplecraft.item.armor;

import com.maplecraft.init.ItemsInit;
import com.maplecraft.item.MapleArmorItem;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.EquipCategory;
import net.minecraft.world.item.crafting.Ingredient;

import static com.maplecraft.utils.AllArmorEquipKeyValues.SHOES_RED_RUBBER_BOOTS_KV;

public class ShoesRedRubberBootsItem extends MapleArmorItem {
    public static String itemName = "shoes_red_rubber_boots";
    public ShoesRedRubberBootsItem() {
        super(itemName,
                new EquipBaseData().category(EquipCategory.SHOES)
                        .levelReq(SHOES_RED_RUBBER_BOOTS_KV.levelReq)
                        .addStat("ARMOR", SHOES_RED_RUBBER_BOOTS_KV.armor)
                        .addStat("MAX HP", SHOES_RED_RUBBER_BOOTS_KV.maxHP)
                        .addStat("STATS", SHOES_RED_RUBBER_BOOTS_KV.stats)
                        .addStat("SPEED", SHOES_RED_RUBBER_BOOTS_KV.speed)
                        .addStat("JUMP", SHOES_RED_RUBBER_BOOTS_KV.jump),
                Ingredient.of(ItemsInit.ETC_BASIC_MONSTER_CRYSTAL.get()));
    }
}
