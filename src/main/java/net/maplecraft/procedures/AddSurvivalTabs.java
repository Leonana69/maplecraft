package net.maplecraft.procedures;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.SkillMenuKeyMessage;
import net.maplecraft.world.customGUI.MapleButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.screens.Screen;

@Mod.EventBusSubscriber
public class AddSurvivalTabs {

    @SubscribeEvent
    public static void onSurvivalInventoryScreenInit(ScreenEvent.Init.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Screen screen = event.getScreen();
        Player player = event.getScreen().getMinecraft().player;
        if (screen instanceof InventoryScreen && player != null && !player.getAbilities().instabuild) {
            AbstractContainerScreen<?> gui = (AbstractContainerScreen<?>) screen;
            event.addListener(new MapleButton(gui.getGuiLeft(), gui.getGuiTop() - 15,
                    0, 0,
                    e -> MapleCraftMod.PACKET_HANDLER.sendToServer(new SkillMenuKeyMessage(0, 0))));
        }
    }
}
