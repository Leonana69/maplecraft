package net.maplecraft.items;

import net.maplecraft.utils.PotionUseItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class UseRedPotionItem extends PotionUseItem {
    public UseRedPotionItem() {
        super(new Item.Properties().rarity(Rarity.COMMON), "red_potion");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        entity.setHealth(entity.getHealth() + 1);
    }

    @Override
    protected boolean canUse(Player player) {
        return player.getHealth() < player.getMaxHealth();
    }
}
