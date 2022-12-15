package net.maplecraft.item.use;

import net.maplecraft.entity.projectile.ArrowForBowEntity;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
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
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new ArrowForBowEntity(world, entity);
    }
}