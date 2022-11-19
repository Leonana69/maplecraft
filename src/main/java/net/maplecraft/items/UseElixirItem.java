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

public class UseElixirItem extends PotionItem {
    public UseElixirItem() {
        super(new MapleItemProperties()
                .itemName("use_elixir")
                .mapleRarity(MapleRarity.EPIC));
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, Player player) {
        player.setHealth(player.getHealth() + player.getMaxHealth() / 2);
        int mana = (int) Variables.get(player, "playerManaPoints") + MapleCraftConstants.MAX_PLAYER_MANA_POINTS / 2;
        Variables.set(player, "playerManaPoints", Math.min(mana, MapleCraftConstants.MAX_PLAYER_MANA_POINTS));
    }

    @Override
    protected boolean canUse(Player player) {
        return player.getHealth() < player.getMaxHealth()
                || (int) Variables.get(player, "playerManaPoints") < MapleCraftConstants.MAX_PLAYER_MANA_POINTS;
    }
}
