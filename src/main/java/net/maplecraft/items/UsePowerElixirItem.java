package net.maplecraft.items;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.MapleCraftConstants;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.PotionItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


public class UsePowerElixirItem extends PotionItem {
    public UsePowerElixirItem() {
        super(new MapleItemProperties()
                .itemName("use_power_elixir")
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
                || (int) Variables.get(player, "playerManaPoints") <= MapleCraftConstants.MAX_PLAYER_MANA_POINTS;
    }
}
