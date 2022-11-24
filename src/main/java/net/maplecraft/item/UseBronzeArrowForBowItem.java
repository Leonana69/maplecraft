package net.maplecraft.item;

import net.maplecraft.entities.BronzeArrowForBowEntity;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
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
    public AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new BronzeArrowForBowEntity(entity, world);
    }
}