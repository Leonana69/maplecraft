package com.maplecraft.procedures;

import com.maplecraft.MapleCraftMod;
import com.maplecraft.inventory.MapleButton;
import com.maplecraft.inventory.MushroomBookViewScreen;
import com.maplecraft.network.QuestMenuOpenMessageHandler;
import com.maplecraft.network.SkillMenuOpenMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class AddMapleButton {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new AddMapleButton());
    }

    @SubscribeEvent
    public void onSurvivalInventoryScreenInit(ScreenEvent.Init.Post event) {
        Screen screen = event.getScreen();
        if (screen instanceof MushroomBookViewScreen) {
            int x = 35;
            int y = 30;
            event.addListener(new MapleButton((screen.width - 192) / 2 + x, y,
                    0, 0,
                    e -> MapleCraftMod.PACKET_HANDLER.sendToServer(new SkillMenuOpenMessageHandler())));

            event.addListener(new MapleButton((screen.width - 192) / 2 + x,  y + 18,
                    32, 0,
                    e -> MapleCraftMod.PACKET_HANDLER.sendToServer(new QuestMenuOpenMessageHandler())));
        }
    }
}
