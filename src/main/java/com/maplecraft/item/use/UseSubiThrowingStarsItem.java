package com.maplecraft.item.use;

import com.maplecraft.entity.projectile.SubiThrowingStarsEntity;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.MapleProjectileItem;
import com.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
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
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new SubiThrowingStarsEntity(world, entity);
    }
}