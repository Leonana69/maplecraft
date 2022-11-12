package dev.maplecraft.items;

import dev.maplecraft.entities.SubiThrowingStarsEntity;
import dev.maplecraft.init.TabsInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class SubiThrowingStarsUseItem extends MapleProjectileItem {
    public SubiThrowingStarsUseItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(64).rarity(Rarity.COMMON));
    }

    @Override
    protected AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new SubiThrowingStarsEntity(entity, world);
    }
}