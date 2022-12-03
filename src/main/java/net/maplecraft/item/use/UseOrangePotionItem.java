package net.maplecraft.item.use;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.MapleCraftConstants;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.PotionItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class UseOrangePotionItem extends PotionItem {
    public static String itemName = "use_orange_potion";
    public UseOrangePotionItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.COMMON));
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, Player player) {
        double mana = (double) Variables.get(player, "playerManaPoints");
        Variables.set(player, "playerManaPoints", Math.min(mana + 2.0D, MapleCraftConstants.MAX_PLAYER_MANA_POINTS));
    }

    @Override
    protected boolean canUse(Player player) {
        return (double) Variables.get(player, "playerManaPoints") < MapleCraftConstants.MAX_PLAYER_MANA_POINTS;
    }
}
