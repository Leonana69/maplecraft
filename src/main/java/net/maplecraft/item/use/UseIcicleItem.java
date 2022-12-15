package net.maplecraft.item.use;

import net.maplecraft.entity.projectile.IcicleEntity;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class UseIcicleItem extends MapleProjectileItem {
    public static String itemName = "use_icicle";
    public UseIcicleItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.RARE));
        this.bonusDamage = 1;
    }

    @Override
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new IcicleEntity(world, entity);
    }
}