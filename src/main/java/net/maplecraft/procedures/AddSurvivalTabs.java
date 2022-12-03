package net.maplecraft.procedures;

import net.maplecraft.MapleCraftMod;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.screens.Screen;
@Mod.EventBusSubscriber
public class AddSurvivalTabs {
    @SubscribeEvent
    public static void onSurvivalInventoryScreenInit(ScreenEvent.Init.Pre event) {
        System.out.println("######## Open screen: " + event.getScreen().getTitle());
    }
}
