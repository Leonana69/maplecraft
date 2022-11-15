package net.maplecraft.items;

import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.MapleArmorItem;

import java.util.List;

public class RedHeadbandHatItem extends MapleArmorItem {
    public RedHeadbandHatItem() {
        super(new Properties().durability(50),
                EquipCategory.HELMET,
                new BonusStats(List.of("DEFENSE"),List.of(1)),
                0.0F, 0.0F);
    }
}
