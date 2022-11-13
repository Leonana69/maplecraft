package net.maplecraft.items;

import net.maplecraft.entities.BalancedFuryEntity;
import net.maplecraft.entities.SteelyThrowingKnivesEntity;
import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleProjectileItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class BalancedFuryUseItem extends MapleProjectileItem {
    public BalancedFuryUseItem() {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(64).rarity(Rarity.EPIC));
        this.bonusDamage = 2;
    }

    @Override
    protected AbstractArrow createArrow(Level world, LivingEntity entity) {
        return new BalancedFuryEntity(entity, world);
    }
}