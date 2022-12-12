package net.maplecraft.procedures;

import net.maplecraft.entities.summon.SummonEntity;
import net.maplecraft.init.EffectsInit;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.maplecraft.client.particle.DamageSkinParticle.spawnDamageParticles;

@Mod.EventBusSubscriber
public class DamageSkinGenerator {
    @SubscribeEvent
    public static void onEntityDamaged(LivingDamageEvent event) {
        assert event != null;
        if (event.getSource().getEntity() instanceof Player player) {
            spawnDamageParticles((int) event.getAmount(), event.getEntity());

            MobEffectInstance instance = player.getEffect(EffectsInit.BUFF_COMBO_ATTACK.get());
            if (instance != null) {
                player.addEffect(new MobEffectInstance(
                        EffectsInit.BUFF_COMBO_ATTACK.get(),
                        instance.getDuration(), // duration in tick
                        Math.min(instance.getAmplifier() + 1, 5),
                        false, true));
            }
        } else if (event.getSource().getEntity() instanceof SummonEntity)
            spawnDamageParticles((int) event.getAmount(), event.getEntity());
    }
}
