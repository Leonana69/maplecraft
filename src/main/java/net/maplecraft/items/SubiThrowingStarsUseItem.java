package net.maplecraft.items;

import net.maplecraft.entities.SubiThrowingStarsEntity;
import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleProjectileItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SubiThrowingStarsUseItem extends MapleProjectileItem {
    public SubiThrowingStarsUseItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(64).rarity(Rarity.COMMON));
        this.bonusDamage = 0.0F;
    }

    @Override
    protected AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new SubiThrowingStarsEntity(entity, world);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("Primitive shuriken"));
        list.add(Component.literal("Attack +0"));
    }
}