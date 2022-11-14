package net.maplecraft.items;

import net.maplecraft.utils.PotionUseItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


public class PowerElixirUseItem extends PotionUseItem {
    public PowerElixirUseItem() {
        super(new Properties().rarity(Rarity.RARE), "power_elixir");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        entity.setHealth(entity.getMaxHealth());
    }
}
