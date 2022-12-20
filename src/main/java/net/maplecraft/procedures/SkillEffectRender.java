package net.maplecraft.procedures;

import net.maplecraft.client.renderer.SkillEffectRenderer;
import net.maplecraft.utils.SkillEffectInstance;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class SkillEffectRender {
    public static List<SkillEffectInstance> hitEffectList = new ArrayList<>();
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new SkillEffectRender());
    }
    @SubscribeEvent
    public void renderSkillEffect(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null)
                return;

            Camera camera = mc.gameRenderer.getMainCamera();
            float currentRenderTick = event.getRenderTick() + event.getPartialTick();
            for (int i = 0; i < hitEffectList.size(); i++) {
                SkillEffectInstance instance = hitEffectList.get(i);
                if (instance.tick < 0) {
                    instance.tick = currentRenderTick;
                }

                if (instance.currentAnime < 0 && instance.tick + instance.delay <= currentRenderTick) {
                    instance.tick = currentRenderTick;
                    instance.currentAnime = 0;
                }

                if (instance.currentAnime >= 0) {
                    SkillEffectRenderer.renderInWorld(event.getPartialTick(), event.getPoseStack(), camera, instance);
                    if (instance.tick + instance.tickPerFrame < currentRenderTick) {
                        instance.currentAnime += 1;
                        instance.tick += instance.tickPerFrame;
                    }

                    if (instance.currentAnime >= instance.animeCount) {
                        hitEffectList.remove(i);
                        i--;
                    }
                }
            }
        }
    }
}
