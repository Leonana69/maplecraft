package net.maplecraft.item;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.PotionItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class UseRedPotionItem extends PotionItem {
    public UseRedPotionItem() {
        super(new MapleItemProperties()
                .itemName("use_red_potion")
                .mapleRarity(MapleRarity.COMMON));
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, Player player) {
        player.setHealth(player.getHealth() + 1);
    }

    @Override
    protected boolean canUse(Player player) {
        return player.getHealth() < player.getMaxHealth();
    }
}
