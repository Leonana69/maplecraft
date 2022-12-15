package net.maplecraft.item.use;

import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.item.PotionItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class UseWhitePotionItem extends PotionItem {
    public static String itemName = "use_white_potion";
    public UseWhitePotionItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.COMMON));
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, Player player) {
        player.setHealth(player.getHealth() + 4);
    }

    @Override
    protected boolean canUse(Player player) {
        return player.getHealth() < player.getMaxHealth();
    }
}
