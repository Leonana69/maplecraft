package com.maplecraft.item;

import com.maplecraft.init.TabsInit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public abstract class PotionItem extends MapleItem {
    public PotionItem(MapleItemProperties itemProperties) {
        super(itemProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 12;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (canUse(player) || player.getAbilities().instabuild) {
            ItemStack itemstack = player.getItemInHand(hand);
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        ItemStack retVal = super.finishUsingItem(itemstack, world, entity);
        if (entity instanceof Player player) {
            potionUseEffect(retVal, world, player);
            if (!player.getAbilities().instabuild)
                retVal.shrink(1);
        }

        return retVal;
    }

    protected abstract void potionUseEffect(ItemStack itemstack, Level world, Player player);
    protected abstract boolean canUse(Player player);
}
