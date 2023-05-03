package com.maplecraft.item.use;

import com.maplecraft.network.Variables;
import com.maplecraft.utils.MapleCraftConstants;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;
import com.maplecraft.item.PotionItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


public class UsePowerElixirItem extends PotionItem {
    public static String itemName = "use_power_elixir";
    public UsePowerElixirItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE));
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, Player player) {
        player.setHealth(player.getMaxHealth());
        Variables.set(player, "playerManaPoints", MapleCraftConstants.MAX_PLAYER_MANA_POINTS);
    }

    @Override
    protected boolean canUse(Player player) {
        return player.getHealth() < player.getMaxHealth()
                || (double) Variables.get(player, "playerManaPoints") < MapleCraftConstants.MAX_PLAYER_MANA_POINTS;
    }
}
