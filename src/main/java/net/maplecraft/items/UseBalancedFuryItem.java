package net.maplecraft.items;

import net.maplecraft.entities.BalancedFuryEntity;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class UseBalancedFuryItem extends MapleProjectileItem {
    public UseBalancedFuryItem() {
        super(new MapleItemProperties()
                .itemName("use_balanced_fury")
                .mapleRarity(MapleRarity.UNIQUE));
        this.bonusDamage = 3;
    }

    @Override
    protected AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new BalancedFuryEntity(entity, world);
    }
}