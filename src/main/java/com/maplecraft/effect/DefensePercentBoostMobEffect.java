package com.maplecraft.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DefensePercentBoostMobEffect extends MapleMobEffect {
    public static int equipValue = 0;
    public static int buffValueP = 0;
    public static int buffValueN = 0;

    public DefensePercentBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        equipValue = amplifier;
    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setAmount(event.getAmount() * (1.0F - (equipValue + buffValueP - buffValueN) / 100.0F));
        }
    }
}
