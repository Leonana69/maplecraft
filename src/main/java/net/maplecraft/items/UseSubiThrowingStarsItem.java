package net.maplecraft.items;

import net.maplecraft.entities.SubiThrowingStarsEntity;
import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class UseSubiThrowingStarsItem extends MapleProjectileItem {
    public UseSubiThrowingStarsItem() {
        super(new MapleItemProperties()
                .itemName("use_subi_throwing_stars")
                .mapleRarity(MapleRarity.COMMON));
        this.bonusDamage = 0;
    }

    @Override
    protected AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new SubiThrowingStarsEntity(entity, world);
    }
}