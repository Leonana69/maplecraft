package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.network.SkillKeyPressMessageHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyMappingsInit {
    private static final Map<Character, Long> lastPress = new HashMap<>();

    public static final KeyMapping SKILL_1_KEY = getKeyMapping('1');
    public static final KeyMapping SKILL_2_KEY = getKeyMapping('2');
    public static final KeyMapping SKILL_3_KEY = getKeyMapping('3');
    public static final KeyMapping SKILL_4_KEY = getKeyMapping('4');

    public static KeyMapping getKeyMapping(char key) {
        return new KeyMapping("key.maplecraft.skill_" + key + "_key", key, "key.categories.misc") {
            private boolean isDownOld = false;

            @Override
            public void setDown(boolean isDown) {
                super.setDown(isDown);
                assert Minecraft.getInstance().player != null;
                if (isDownOld != isDown && isDown) {
                    MapleCraftMod.PACKET_HANDLER.sendToServer(new SkillKeyPressMessageHandler(0, 0, key));
                    SkillKeyPressMessageHandler.pressAction(Minecraft.getInstance().player, 0, 0, key);
                    lastPress.put(key, System.currentTimeMillis());
                } else if (isDownOld != isDown) {
                    int dt = (int) (System.currentTimeMillis() - lastPress.get(key));
                    MapleCraftMod.PACKET_HANDLER.sendToServer(new SkillKeyPressMessageHandler(1, dt, key));
                    assert Minecraft.getInstance().player != null;
                    SkillKeyPressMessageHandler.pressAction(Minecraft.getInstance().player, 1, dt, key);
                }
                isDownOld = isDown;
            }
        };
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SKILL_1_KEY);
        event.register(SKILL_2_KEY);
        event.register(SKILL_3_KEY);
        event.register(SKILL_4_KEY);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null) {
                SKILL_1_KEY.consumeClick();
                SKILL_2_KEY.consumeClick();
                SKILL_3_KEY.consumeClick();
                SKILL_4_KEY.consumeClick();
            }
        }
    }
}
