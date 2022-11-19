package net.maplecraft.item;

import net.maplecraft.entities.SteelyThrowingKnivesEntity;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class UseSteelyThrowingKnivesItem extends MapleProjectileItem {
    public UseSteelyThrowingKnivesItem() {
        super(new MapleItemProperties()
                .itemName("use_steely_throwing_knives")
                .mapleRarity(MapleRarity.EPIC));
        this.bonusDamage = 1;
    }

    @Override
    protected AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new SteelyThrowingKnivesEntity(entity, world);
    }
}