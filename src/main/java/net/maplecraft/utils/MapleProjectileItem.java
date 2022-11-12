package net.maplecraft.utils;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public abstract class MapleProjectileItem extends Item {
    public float bonusDamage = 0.0F;
    public MapleProjectileItem(Properties itemProperties) {
        super(itemProperties);
    }
    protected abstract AbstractArrow createArrow(Level world, LivingEntity entity);
}
