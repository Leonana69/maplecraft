package com.maplecraft.item.use;

import com.maplecraft.network.Variables;
import com.maplecraft.utils.MapleCraftConstants;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;
import com.maplecraft.item.PotionItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class UseManaElixirItem extends PotionItem {
    public static String itemName = "use_mana_elixir";
    public UseManaElixirItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC));
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, Player player) {
        double mana = (double) Variables.get(player, "playerManaPoints") + MapleCraftConstants.MAX_PLAYER_MANA_POINTS / 2;
        Variables.set(player, "playerManaPoints", Math.min(mana, MapleCraftConstants.MAX_PLAYER_MANA_POINTS));
    }

    @Override
    protected boolean canUse(Player player) {
        return (double) Variables.get(player, "playerManaPoints") < MapleCraftConstants.MAX_PLAYER_MANA_POINTS;
    }
}
