package com.maplecraft.effect.equipEffect;

import com.maplecraft.effect.MapleMobEffect;
import com.maplecraft.init.EffectsInit;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EquipStatsPercentBoostMobEffect extends MapleMobEffect {
    public EquipStatsPercentBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
    }

    @Override
    public String getDescriptionId() {
        return "effect.maplecraft.equip_stats_percent_boost";
    }

    @SubscribeEvent
    public static void onPlayerDigging(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player.hasEffect(EffectsInit.EQUIP_STATS_PERCENT_BOOST.get())) {
            MobEffectInstance instance = player.getEffect(EffectsInit.EQUIP_STATS_PERCENT_BOOST.get());
            if (instance != null) {
                event.setNewSpeed(event.getOriginalSpeed() * (1 + instance.getAmplifier() / 100F));
            }
        }
    }
}
