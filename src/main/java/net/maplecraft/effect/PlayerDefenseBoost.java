package net.maplecraft.effect;

import net.maplecraft.network.Variables;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod.EventBusSubscriber
public class PlayerDefenseBoost {
    public static int equipDefensePercentBoostBase = 1;
    public static int sourceCount = 2;
    public static class DefenseBuff {
        public int amount = 0;
        public int tick = 0;

        @Override
        public String toString() {
            return amount + "&" + tick;
        }
    }

    public static String getDefaultDefenseBuff() {
        return new DefenseBuff() + "&" + new DefenseBuff();
    }

    public static int [] fromString(String s) {
        String [] sa = s.split("&");
        return Arrays.stream(sa).mapToInt(Integer::parseInt).toArray();
    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            int [] defenseBoost = PlayerDefenseBoost.fromString((String) Variables.get(player, "defenseBoost"));
            float sum = 0;
            for (int i = 0; i < sourceCount; i++) {
                sum += defenseBoost[i * 2];
            }
            event.setAmount(event.getAmount() * (1.0F - sum / 100.0F));
            System.out.println("defense: " + sum);
        }
    }

    public static int [] apply(int index, int a, int t, int [] defenseBoost) {
        assert index >= 0 && index < sourceCount;
        int amount = defenseBoost[index * 2];
        int tick = defenseBoost[index * 2 + 1];
        if (a >= amount && t >= tick) {
            defenseBoost[index * 2] = a;
            defenseBoost[index * 2 + 1] = t;
        }
        return defenseBoost;
    }

    public static String tick(int [] defenseBoost) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < sourceCount; i++) {
            defenseBoost[i * 2 + 1]--;
            if (defenseBoost[i * 2 + 1] <= 0) {
                defenseBoost[i * 2] = 0;
                defenseBoost[i * 2 + 1] = 0;
            }
            s.append(defenseBoost[i * 2]);
            s.append("&");
            s.append(defenseBoost[i * 2 + 1]);
            s.append("&");
        }
        return s.toString();
    }
}
