package net.maplecraft.init;

import net.maplecraft.client.screens.CubeGUIMenuScreen;
import net.maplecraft.client.screens.SkillGUIMenuScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ScreensInit {
    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(GUIMenuInit.CUBE_GUI_MENU.get(), CubeGUIMenuScreen::new);
            MenuScreens.register(GUIMenuInit.SKILL_GUI_MENU.get(), SkillGUIMenuScreen::new);
        });
    }
}
