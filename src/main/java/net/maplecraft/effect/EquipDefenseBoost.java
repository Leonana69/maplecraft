package net.maplecraft.effect;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.EquipMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EquipDefenseBoost {
    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            float defenseBoost = (float)(double) Variables.get(player, "defenseBoost");
            event.setAmount(event.getAmount() * (1.0F - defenseBoost / 100.0F));
        }
    }
}
