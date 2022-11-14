package net.maplecraft.items;

import net.maplecraft.entities.SubiThrowingStarsEntity;
import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleProjectileItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class SubiThrowingStarsUseItem extends MapleProjectileItem {
    public SubiThrowingStarsUseItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(64).rarity(Rarity.COMMON));
        this.bonusDamage = 0;
    }

    @Override
    protected AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new SubiThrowingStarsEntity(entity, world);
    }
}