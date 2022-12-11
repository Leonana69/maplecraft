package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.client.renderer.*;
import net.maplecraft.entities.boss.zakum.BossZakumBodyEntityRenderer;
import net.maplecraft.entities.boss.zakum.BossZakumLeftHandEntityRenderer;
import net.maplecraft.entities.boss.zakum.BossZakumRightHandEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MapleCraftMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderersInit {
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntitiesInit.SUBI_THROWING_STARS_ENTITY.get(), SubiThrowingStarsRenderer::new);
        event.registerEntityRenderer(EntitiesInit.ICICLE_ENTITY.get(), IcicleRenderer::new);
        event.registerEntityRenderer(EntitiesInit.STEELY_THROWING_KNIVES_ENTITY.get(), SteelyThrowingKnivesRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BALANCED_FURY_ENTITY.get(), BalancedFuryRenderer::new);

        event.registerEntityRenderer(EntitiesInit.ARROW_FOR_BOW_ENTITY.get(), ArrowForBowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BRONZE_ARROW_FOR_BOW_ENTITY.get(), BronzeArrowForBowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.STEEL_ARROW_FOR_BOW_ENTITY.get(), SteelArrowForBowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.DIAMOND_ARROW_FOR_BOW_ENTITY.get(), DiamondArrowForBowRenderer::new);

        event.registerEntityRenderer(EntitiesInit.BOMB_ARROW_ENTITY.get(), BombArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.HOLY_ARROW_ENTITY.get(), HolyArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.FIRE_ARROW_ENTITY.get(), FireArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.POISON_BRACE_ENTITY.get(), PoisonBraceRenderer::new);
        event.registerEntityRenderer(EntitiesInit.AVENGER_ENTITY.get(), AvengerRenderer::new);

        event.registerEntityRenderer(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY.get(), BossZakumBodyEntityRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BOSS_ZAKUM_LEFT_HAND_ENTITY.get(), BossZakumLeftHandEntityRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BOSS_ZAKUM_RIGHT_HAND_ENTITY.get(), BossZakumRightHandEntityRenderer::new);
    }
}