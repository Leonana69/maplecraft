package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.client.renderer.BalancedFuryRenderer;
import net.maplecraft.client.renderer.SteelyThrowingKnivesRenderer;
import net.maplecraft.client.renderer.SubiThrowingStarsRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MapleCraftMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderersInit {
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntitiesInit.SUBI_THROWING_STARS_ENTITY.get(), SubiThrowingStarsRenderer::new);
        event.registerEntityRenderer(EntitiesInit.STEELY_THROWING_KNIVES_ENTITY.get(), SteelyThrowingKnivesRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BALANCED_FURY_ENTITY.get(), BalancedFuryRenderer::new);
    }
}