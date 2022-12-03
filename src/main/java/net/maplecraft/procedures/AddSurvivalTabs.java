package net.maplecraft.procedures;

import net.maplecraft.MapleCraftMod;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.screens.Screen;
@Mod.EventBusSubscriber
public class AddSurvivalTabs {
    @SubscribeEvent
    public static void onSurvivalInventoryScreenInit(ScreenEvent.Init.Pre event) {
        Screen screen = event.getScreen();
        Player player = event.getScreen().getMinecraft().player;
        if (screen instanceof InventoryScreen && player != null && !player.getAbilities().instabuild) {
            System.out.println("######## Open survival screen: " + event.getScreen().getTitle());

        }

    }
}
