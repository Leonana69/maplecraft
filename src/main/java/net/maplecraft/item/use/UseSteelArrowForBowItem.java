package net.maplecraft.item.use;

import net.maplecraft.entity.projectile.SteelArrowForBowEntity;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class UseSteelArrowForBowItem extends MapleProjectileItem {
    public static String itemName = "use_steel_arrow_for_bow";
    public UseSteelArrowForBowItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC));
        this.bonusDamage = 2;
    }

    @Override
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new SteelArrowForBowEntity(world, entity);
    }
}