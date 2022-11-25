package net.maplecraft.item;

import net.maplecraft.entities.DiamondArrowForBowEntity;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class UseDiamondArrowForBowItem extends MapleProjectileItem {
    public static String itemName = "use_diamond_arrow_for_bow";
    public UseDiamondArrowForBowItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE));
        this.bonusDamage = 3;
    }

    @Override
    public AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new DiamondArrowForBowEntity(world, entity);
    }
}