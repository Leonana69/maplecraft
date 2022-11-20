package net.maplecraft.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.maplecraft.client.particle.DamageSkinParticle.spawnDamageParticles;

@Mod.EventBusSubscriber
public class ShowDamage {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        assert event != null;
        if (event.getSource().getEntity() instanceof Player) {
            spawnDamageParticles((int) event.getAmount(), event.getEntity());
        }
    }
}
