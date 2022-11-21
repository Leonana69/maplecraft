package net.maplecraft.item;

import net.maplecraft.entities.SubiThrowingStarsEntity;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class UseSubiThrowingStarsItem extends MapleProjectileItem {
    public static String itemName = "use_subi_throwing_stars";
    public UseSubiThrowingStarsItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.COMMON));
        this.bonusDamage = 0;
    }

    @Override
    protected AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new SubiThrowingStarsEntity(entity, world);
    }
}