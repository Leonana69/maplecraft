package com.maplecraft.item.use;

import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.item.MapleProjectileItem;
import com.maplecraft.utils.MapleRarity;
import com.maplecraft.entity.projectile.SteelArrowForBowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class UseSteelArrowForBowItem extends MapleProjectileItem {
    public static String itemName = "use_steel_arrow_for_bow";
    public UseSteelArrowForBowItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC));
        this.bonusDamage = 2;
    }

    @Override
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new SteelArrowForBowEntity(world, entity);
    }
}