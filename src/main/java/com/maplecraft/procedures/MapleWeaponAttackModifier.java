package com.maplecraft.procedures;

import com.maplecraft.item.WeaponBowItem;
import com.maplecraft.item.WeaponClawItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MapleWeaponAttackModifier {
    @SubscribeEvent
    public static void attackEntityEvent(AttackEntityEvent event) {
        ItemStack itemStack = event.getEntity().getMainHandItem();
        // disable bow and claw's melee attack
        if (itemStack.getItem() instanceof WeaponClawItem
                || itemStack.getItem() instanceof WeaponBowItem)
            event.setCanceled(true);
    }
}
