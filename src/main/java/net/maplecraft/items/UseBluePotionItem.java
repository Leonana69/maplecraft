package net.maplecraft.items;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.MapleCraftConstants;
import net.maplecraft.utils.PotionUseItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class UseBluePotionItem extends PotionUseItem {
    public UseBluePotionItem() {
        super(new Properties().rarity(Rarity.COMMON), "blue_potion");
    }

    @Override
    protected void potionUseEffect(ItemStack itemstack, Level world, LivingEntity entity) {
        int mana = (int) Variables.get(entity, "playerManaPoints");

        if (mana < MapleCraftConstants.MAX_PLAYER_MANA_POINTS) {
            Variables.set(entity, "playerManaPoints", mana + 1);
        } else {
            Variables.set(entity, "playerManaPoints", 0);
        }
    }

    @Override
    protected boolean canUse(Player player) {
        return (int) Variables.get(player, "playerManaPoints") <= MapleCraftConstants.MAX_PLAYER_MANA_POINTS;
    }
}
