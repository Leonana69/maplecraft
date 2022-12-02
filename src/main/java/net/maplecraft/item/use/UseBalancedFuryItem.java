package net.maplecraft.item.use;

import net.maplecraft.entities.BalancedFuryEntity;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileEntity;
import net.maplecraft.utils.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
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