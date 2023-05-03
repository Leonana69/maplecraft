package com.maplecraft.procedures;

import com.maplecraft.client.particle.BasicDamageSkinParticle;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.entity.summon.SummonEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

    public static void spawnDamageParticles(int damage, Entity source, LivingEntity target) {
        if (target.level instanceof ServerLevel _level) {
            Vec3 view = source.getViewVector(1).scale(0.6);
            int cnt = (int) Math.log10(damage) + 1;

            for (int i = 0; i < cnt; i++) {
                int digit = damage % 10;
                damage /= 10;
                _level.sendParticles(BasicDamageSkinParticle.P.get(digit),
                        target.getX() + (i - cnt / 2.0) * view.z + view.x * (1 + i * 0.1),
                        target.getY() + target.getBbHeight() + 1 + (i % 2) * 0.08,
                        target.getZ() - (i - cnt / 2.0) * view.x + view.z * (1 + i * 0.1),
                        1, 0.001, 0.001, 0.001, 0);

            }
        }
    }
}
