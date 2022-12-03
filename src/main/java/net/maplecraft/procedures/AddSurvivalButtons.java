package net.maplecraft.procedures;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.SkillMenuKeyMessage;
import net.maplecraft.world.customGUI.MapleButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.screens.Screen;

@Mod.EventBusSubscriber
public class AddSurvivalButtons {
    @SubscribeEvent
    public static void onSurvivalInventoryScreenInit(ScreenEvent.Init.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Screen screen = event.getScreen();
        Player player = event.getScreen().getMinecraft().player;
        if (screen instanceof InventoryScreen || screen instanceof CreativeModeInventoryScreen) {
            boolean isCreative = screen instanceof CreativeModeInventoryScreen;
            AbstractContainerScreen<?> gui = (AbstractContainerScreen<?>) screen;
            int x = isCreative ? -20 : 0;
            int y = isCreative ? 0 : -15;

            event.addListener(new MapleButton(gui.getGuiLeft() + x, gui.getGuiTop() + y,
                    0, 0,
                    e -> MapleCraftMod.PACKET_HANDLER.sendToServer(new SkillMenuKeyMessage())));
        }
    }
}