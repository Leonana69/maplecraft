package net.maplecraft.item.use;

import net.maplecraft.entity.projectile.SubiThrowingStarsEntity;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
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