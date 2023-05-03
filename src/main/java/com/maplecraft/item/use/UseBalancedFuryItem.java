package com.maplecraft.item.use;

import com.maplecraft.entity.projectile.BalancedFuryEntity;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.MapleProjectileItem;
import com.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class UseBalancedFuryItem extends MapleProjectileItem {
    public static String itemName = "use_balanced_fury";
    public UseBalancedFuryItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE));
        this.bonusDamage = 3;
    }

    @Override
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new BalancedFuryEntity(world, entity);
    }
}