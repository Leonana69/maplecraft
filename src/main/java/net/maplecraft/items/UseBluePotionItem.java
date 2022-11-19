package net.maplecraft.items;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.MapleCraftConstants;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.PotionItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class UseBluePotionItem extends PotionItem {
    public UseBluePotionItem() {
        super(new MapleItemProperties()
                .itemName("use_blue_potion")
                .mapleRarity(MapleRarity.COMMON));
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, Player player) {
        int mana = (int) Variables.get(player, "playerManaPoints");
        Variables.set(player, "playerManaPoints", mana + 1);
    }

    @Override
    protected boolean canUse(Player player) {
        return (int) Variables.get(player, "playerManaPoints") < MapleCraftConstants.MAX_PLAYER_MANA_POINTS;
    }
}
