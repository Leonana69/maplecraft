package net.maplecraft.procedures;

import net.maplecraft.init.EffectsInit;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.IBaseEquip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
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
    public static void onSoundPlayEvent(PlaySoundEvent event) {
        if (event.getName().equals("entity.player.attack.sweep")) {
            Player player = Minecraft.getInstance().player;
            if (player != null && player.getMainHandItem().getItem() instanceof IBaseEquip equip) {
                String soundPath = EquipCategory.getAttackSound(equip.getBaseEquipData().category);
                if (soundPath != null) {
                    event.setSound(new SimpleSoundInstance(
                            new ResourceLocation("maplecraft:" + soundPath),
                            SoundSource.PLAYERS,
                            1, 1, SoundInstance.createUnseededRandom(),
                            false, 0,
                            SoundInstance.Attenuation.NONE,
                            player.getX(), player.getY(), player.getZ(), true));
                }
            }
        }
    }
}
