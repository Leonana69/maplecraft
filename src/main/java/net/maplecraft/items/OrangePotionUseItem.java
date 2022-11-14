package net.maplecraft.items;

import net.maplecraft.utils.PotionUseItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class OrangePotionUseItem extends PotionUseItem {
    public OrangePotionUseItem() {
        super(new Properties().rarity(Rarity.COMMON), "orange_potion");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        entity.setHealth(entity.getHealth() + 2);
    }
}
