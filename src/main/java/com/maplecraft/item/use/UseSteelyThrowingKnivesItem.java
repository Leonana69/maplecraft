package com.maplecraft.item.use;

import com.maplecraft.entity.projectile.SteelyThrowingKnivesEntity;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.MapleProjectileItem;
import com.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class UseSteelyThrowingKnivesItem extends MapleProjectileItem {
    public static String itemName = "use_steely_throwing_knives";
    public UseSteelyThrowingKnivesItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC));
        this.bonusDamage = 2;
    }

    @Override
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new SteelyThrowingKnivesEntity(world, entity);
    }
}