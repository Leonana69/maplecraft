package net.maplecraft.items;

import net.maplecraft.utils.PotionUseItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class UseAllCurePotionItem extends PotionUseItem {
    public UseAllCurePotionItem() {
        super(new Properties().rarity(Rarity.RARE), "all_cure_potion");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        entity.removeAllEffects();
    }

    @Override
    protected boolean canUse(Player player) {
        return true;
    }
}