package net.maplecraft.item.use;

import net.maplecraft.entities.ArrowForBowEntity;
import net.maplecraft.entities.SubiThrowingStarsEntity;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
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
    public AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new ArrowForBowEntity(world, entity);
    }
}