package net.maplecraft.effect;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerDefenseBoost {
    public static int sourceCount = 2;
    public static class DefenseBuff {
        public float amount = 0;
        public int tick = 0;
    }

    public static DefenseBuff [] buff = new DefenseBuff[] {
            new DefenseBuff(),
            new DefenseBuff(),
    };

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player) {
            float sum = 0;
            for (int i = 0; i < sourceCount; i++) {
                sum += buff[i].amount;
            }
            event.setAmount(event.getAmount() * (1.0F - sum / 100.0F));
        }
    }

    public static void apply(int index, float f, int i) {
        assert index >= 0 && index < sourceCount;
        if (f >= buff[index].amount && i >= buff[index].tick) {
            buff[index].amount = f;
            buff[index].tick = i;
        }
    }

    public static void tick() {
        for (int i = 0; i < sourceCount; i++) {
            buff[i].tick--;
            if (buff[i].tick <= 0) {
                buff[i].amount = 0;
            }
        }
    }
}
