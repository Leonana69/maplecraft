package dev.maplecraft.items;

import dev.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class PowerElixirUseItem extends Item {
    public PowerElixirUseItem() {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                .stacksTo(64)
                .rarity(Rarity.RARE)
                .food((new FoodProperties.Builder())
                        .nutrition(0)
                        .saturationMod(0.3f)
                        .build()));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 10;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("Recovers 100% of Max HP"));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        ItemStack retVal = super.finishUsingItem(itemstack, world, entity);
        entity.setHealth((float) entity.getMaxHealth());
        return retVal;
    }
}
