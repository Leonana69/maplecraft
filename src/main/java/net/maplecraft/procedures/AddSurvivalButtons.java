package net.maplecraft.procedures;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.QuestMenuOpenMessageHandler;
import net.maplecraft.network.SkillMenuOpenMessageHandler;
import net.maplecraft.inventory.MapleButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.screens.Screen;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class AddSurvivalButtons {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new AddSurvivalButtons());
    }

    @SubscribeEvent
    public void onSurvivalInventoryScreenInit(ScreenEvent.Init.Post event) {
        Screen screen = event.getScreen();
        if (screen instanceof InventoryScreen || screen instanceof CreativeModeInventoryScreen) {
            boolean isCreative = screen instanceof CreativeModeInventoryScreen;
            AbstractContainerScreen<?> gui = (AbstractContainerScreen<?>) screen;
            int x = isCreative ? -18 : 0;
            int y = isCreative ? 0 : -13;
            int dx = isCreative ? 0 : 18;
            int dy = isCreative ? 15 : 0;

            event.addListener(new MapleButton(gui.getGuiLeft() + x, gui.getGuiTop() + y,
                    0, 0,
                    e -> MapleCraftMod.PACKET_HANDLER.sendToServer(new SkillMenuOpenMessageHandler())));

            event.addListener(new MapleButton(gui.getGuiLeft() + x + dx, gui.getGuiTop() + y + dy,
                    32, 0,
                    e -> MapleCraftMod.PACKET_HANDLER.sendToServer(new QuestMenuOpenMessageHandler())));
        }
    }
}
