package net.maplecraft.item.use;

import net.maplecraft.entity.projectile.SteelyThrowingKnivesEntity;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
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