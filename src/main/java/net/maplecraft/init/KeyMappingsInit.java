package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.Skill1KeyMessage;
import net.maplecraft.network.SkillMenuKeyMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyMappingsInit {
    private static long SKILL_1_LAST_PRESS = 0;

    public static final KeyMapping SKILL_MENU_KEY = new KeyMapping("key.maplecraft.skill_menu_key", GLFW.GLFW_KEY_K, "key.categories.misc") {
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            assert Minecraft.getInstance().player != null;
            if (isDownOld != isDown && isDown) {
                MapleCraftMod.PACKET_HANDLER.sendToServer(new SkillMenuKeyMessage(0, 0));
                SkillMenuKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };

    public static final KeyMapping SKILL_1_KEY = new KeyMapping("key.maplecraft.skill_1_key", GLFW.GLFW_KEY_1, "key.categories.misc") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            assert Minecraft.getInstance().player != null;
            if (isDownOld != isDown && isDown) {
                MapleCraftMod.PACKET_HANDLER.sendToServer(new Skill1KeyMessage(0, 0));
                Skill1KeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                SKILL_1_LAST_PRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown) {
                int dt = (int) (System.currentTimeMillis() - SKILL_1_LAST_PRESS);
                MapleCraftMod.PACKET_HANDLER.sendToServer(new Skill1KeyMessage(1, dt));
                assert Minecraft.getInstance().player != null;
                Skill1KeyMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SKILL_MENU_KEY);
        event.register(SKILL_1_KEY);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null) {
                SKILL_MENU_KEY.consumeClick();
                SKILL_1_KEY.consumeClick();
            }
        }
    }
}
