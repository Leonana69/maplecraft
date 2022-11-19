package net.maplecraft.item;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.PotionItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class UseAllCurePotionItem extends PotionItem {
    public UseAllCurePotionItem() {
        super(new MapleItemProperties()
                .itemName("use_all_cure_potion")
                .mapleRarity(MapleRarity.EPIC));
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, Player player) {
        player.removeAllEffects();
    }

    @Override
    protected boolean canUse(Player player) {
        return true;
    }
}
