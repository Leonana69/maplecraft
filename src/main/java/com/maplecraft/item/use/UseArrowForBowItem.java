package com.maplecraft.item.use;

import com.maplecraft.entity.projectile.ArrowForBowEntity;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.MapleProjectileItem;
import com.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class UseArrowForBowItem extends MapleProjectileItem {
    public static String itemName = "use_arrow_for_bow";
    public UseArrowForBowItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.COMMON));
        this.bonusDamage = 0;
    }

    @Override
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new ArrowForBowEntity(world, entity);
    }
}