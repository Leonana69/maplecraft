package net.maplecraft.items;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.MapleCraftConstants;
import net.maplecraft.utils.PotionUseItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class UseElixirItem extends PotionUseItem {
    public UseElixirItem() {
        super(new Item.Properties().rarity(Rarity.RARE), "elixir");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        entity.setHealth(entity.getHealth() + entity.getMaxHealth() / 2);
    }

    @Override
    protected boolean canUse(Player player) {
        return player.getHealth() < player.getMaxHealth()
                || (int) Variables.get(player, "playerManaPoints") <= MapleCraftConstants.MAX_PLAYER_MANA_POINTS;
    }
}
