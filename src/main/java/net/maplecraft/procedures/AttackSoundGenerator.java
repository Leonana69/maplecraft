package net.maplecraft.procedures;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.IBaseEquip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber
public class AttackSoundGenerator {
    @SubscribeEvent
    public static void onSoundPlayed(PlaySoundEvent event) {
        SoundInstance soundInstance = event.getSound();
        if (soundInstance != null && soundInstance.getSound() != null) {
            System.out.println(soundInstance.getSound().getPath());
//            if (soundInstance.getSound().getLocation().getPath().contains("entity/player/attack/sweep")) {
//                System.out.println("cancel it");
//                event.setCanceled(true);
////            Player player = Minecraft.getInstance().player;
////            if (player != null && player.getMainHandItem().getItem() instanceof IBaseEquip equip) {
////                String soundPath = EquipCategory.getAttackSound(equip.getBaseEquipData().category);
////                if (soundPath != null) {
////                    player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
////                            Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:" + soundPath))),
////                            SoundSource.PLAYERS, 1, 1);
////                }
////            }
//            }
        } else {
            System.out.println("null sound");
        }

    }
}
