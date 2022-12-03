package net.maplecraft.item.use;

import net.maplecraft.entities.IcicleEntity;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileEntity;
import net.maplecraft.utils.MapleProjectileItem;
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