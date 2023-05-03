package com.maplecraft.item;

import com.maplecraft.init.TabsInit;
import com.maplecraft.entity.MapleProjectileEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class MapleProjectileItem extends MapleItem {
    public int bonusDamage = 0;
    public MapleProjectileItem(MapleItemProperties itemProperties) {
        super(itemProperties.properties(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(64)));
    }
    public abstract MapleProjectileEntity createArrow(Level world, LivingEntity entity);

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("Attack +" + bonusDamage));
    }
}
