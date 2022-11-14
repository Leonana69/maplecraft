package net.maplecraft.items;

import net.maplecraft.utils.PotionUseItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class ElixirUseItem extends PotionUseItem {
    public ElixirUseItem() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON), "elixir");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        entity.setHealth(entity.getHealth() + entity.getMaxHealth() / 2);
    }
}
