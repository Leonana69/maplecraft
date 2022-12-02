package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class JumpPercentBoostMobEffect extends MapleMobEffect {
    public static int equipValue = 0;
    public static int buffValue = 0;

    public JumpPercentBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        equipValue = amplifier;
    }

    @SubscribeEvent
    public static void onLivingJumpEvent(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof Player player) {
            System.out.println("buff: " + buffValue + ", equip: " + equipValue);
            player.setDeltaMovement(player.getDeltaMovement()
                    .scale(1 + (equipValue + buffValue) / 100.0D));
        }
    }
}
