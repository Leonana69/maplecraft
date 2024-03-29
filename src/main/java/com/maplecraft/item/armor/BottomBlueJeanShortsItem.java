package com.maplecraft.item.armor;

import com.maplecraft.init.ItemsInit;
import com.maplecraft.item.MapleArmorItem;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.EquipCategory;
import net.minecraft.world.item.crafting.Ingredient;

import static com.maplecraft.utils.AllArmorEquipKeyValues.BOTTOM_BLUE_JEAN_SHORTS_KV;

public class BottomBlueJeanShortsItem extends MapleArmorItem {
    public static String itemName = "bottom_blue_jean_shorts";
    public BottomBlueJeanShortsItem() {
        super(itemName,
                new EquipBaseData().category(EquipCategory.BOTTOM)
                        .levelReq(BOTTOM_BLUE_JEAN_SHORTS_KV.levelReq)
                        .addStat("ARMOR", BOTTOM_BLUE_JEAN_SHORTS_KV.armor)
                        .addStat("MAX HP", BOTTOM_BLUE_JEAN_SHORTS_KV.maxHP)
                        .addStat("STATS", BOTTOM_BLUE_JEAN_SHORTS_KV.stats)
                        .addStat("SPEED", BOTTOM_BLUE_JEAN_SHORTS_KV.speed)
                        .addStat("JUMP", BOTTOM_BLUE_JEAN_SHORTS_KV.jump),
                Ingredient.of(ItemsInit.ETC_BASIC_MONSTER_CRYSTAL.get()));
    }
}
