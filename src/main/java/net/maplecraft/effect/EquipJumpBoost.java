package net.maplecraft.effect;

import net.maplecraft.network.Variables;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EquipJumpBoost {
    public static int equipJumpPercentBoostBase = 1;
    @SubscribeEvent
    public static void onLivingJumpEvent(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof Player player) {
            double jumpBoost = (double) Variables.get(player, "jumpBoost");
            player.setDeltaMovement(player.getDeltaMovement().add(
                    0.0D, jumpBoost / 70.0D, 0.0D));
        }
    }
}
