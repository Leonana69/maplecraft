package net.maplecraft.items;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.PotionUseItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;

public class RedPotionUseItem extends PotionUseItem {
    public RedPotionUseItem() {
        super(new Item.Properties().rarity(Rarity.COMMON), "red_potion");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        // TODO: remove
        if (world instanceof ServerLevel) {
            if (entity.getMaxHealth() >= 100) {
                entity.getAttribute(MAX_HEALTH).setBaseValue(20);
            } else
                entity.getAttribute(MAX_HEALTH).setBaseValue(entity.getMaxHealth() + 20);
        }

        Variables.set(entity, "playerManaPoints", 12);

        entity.setHealth(entity.getHealth() + 1);
    }
}
