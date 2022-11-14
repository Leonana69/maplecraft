package net.maplecraft.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class MapleProjectileItem extends Item {
    public int bonusDamage = 0;
    public MapleProjectileItem(Properties itemProperties) {
        super(itemProperties);
    }
    protected abstract AbstractArrow createArrow(Level world, LivingEntity entity);

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("Attack +" + bonusDamage));
    }
}
