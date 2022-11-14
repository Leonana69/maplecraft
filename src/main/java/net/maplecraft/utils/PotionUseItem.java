package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class PotionUseItem extends Item {
    public String potionName;
    public PotionUseItem(Properties itemProperties, String name) {
        super(itemProperties.tab(TabsInit.TAB_MAPLE_CRAFT)
                .stacksTo(64));
        potionName = name;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 15;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.maplecraft.use_" + potionName + "_description"));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        ItemStack retVal = super.finishUsingItem(itemstack, world, entity);
        potionUseEffect(retVal, world, entity);
        if (!(entity instanceof Player) || !((Player)entity).getAbilities().instabuild) {
            retVal.shrink(1);
        }
        return retVal;
    }

    protected abstract void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity);
}
