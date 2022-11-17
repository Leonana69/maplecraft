package net.maplecraft.items;

import net.maplecraft.utils.PotionUseItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class UseWhitePotionItem extends PotionUseItem {
    public UseWhitePotionItem() {
        super(new Properties().rarity(Rarity.COMMON), "white_potion");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        entity.setHealth(entity.getHealth() + 3);
    }

    @Override
    protected boolean canUse(Player player) {
        return player.getHealth() < player.getMaxHealth();
    }
}
