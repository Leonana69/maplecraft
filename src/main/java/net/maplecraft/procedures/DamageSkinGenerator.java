package net.maplecraft.procedures;

import net.maplecraft.entity.summon.SummonEntity;
import net.maplecraft.init.EffectsInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.maplecraft.client.particle.DamageSkinParticle.spawnDamageParticles;

@Mod.EventBusSubscriber
public class DamageSkinGenerator {
    @SubscribeEvent
    public static void onEntityDamaged(LivingDamageEvent event) {
        assert event != null;
        Entity source = event.getSource().getEntity();
        LivingEntity target = event.getEntity();
        if (source instanceof Player player) {
            spawnDamageParticles((int) event.getAmount(), player, target);
            MobEffectInstance instance = player.getEffect(EffectsInit.BUFF_COMBO_ATTACK.get());
            if (instance != null) {
                player.addEffect(new MobEffectInstance(
                        EffectsInit.BUFF_COMBO_ATTACK.get(),
                        instance.getDuration(), // duration in tick
                        Math.min(instance.getAmplifier() + 1, 5),
                        false, true));
            }
        } else if (source instanceof SummonEntity summon && summon.getOwner() != null) {
            spawnDamageParticles((int) event.getAmount(), summon.getOwner(), target);
        }
    }
}
