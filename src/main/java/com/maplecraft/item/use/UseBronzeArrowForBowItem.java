package com.maplecraft.item.use;

import com.maplecraft.entity.projectile.BronzeArrowForBowEntity;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.MapleProjectileItem;
import com.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class UseBronzeArrowForBowItem extends MapleProjectileItem {
    public static String itemName = "use_bronze_arrow_for_bow";
    public UseBronzeArrowForBowItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.RARE));
        this.bonusDamage = 1;
    }

    @Override
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new BronzeArrowForBowEntity(world, entity);
    }
}