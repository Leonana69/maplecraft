package com.maplecraft.init;

import com.maplecraft.client.screens.CubeScreen;
import com.maplecraft.client.screens.QuestScreen;
import com.maplecraft.client.screens.SkillScreen;
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
            MenuScreens.register(MenusInit.CUBE_MENU.get(), CubeScreen::new);
            MenuScreens.register(MenusInit.SKILL_MENU.get(), SkillScreen::new);
            MenuScreens.register(MenusInit.QUEST_MENU.get(), QuestScreen::new);
        });
    }
}
