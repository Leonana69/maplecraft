package net.maplecraft.items;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.MapleCraftConstants;
import net.maplecraft.utils.PotionUseItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class BluePotionUseItem extends PotionUseItem {
    public BluePotionUseItem() {
        super(new Properties().rarity(Rarity.COMMON), "blue_potion");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        int mana = (int) Variables.get(entity, "playerManaPoints");

        if (mana < MapleCraftConstants.MAX_PLAYER_MANA_POINTS) {
            entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.playerManaPoints = mana + 1;
                capability.syncPlayerVariables(entity);
            });
        }
    }
}
