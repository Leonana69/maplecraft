package com.maplecraft.init;

import com.maplecraft.client.renderer.*;
import com.maplecraft.entity.boss.zakum.BossZakumBodyEntityRenderer;
import com.maplecraft.entity.boss.zakum.BossZakumLeftHandEntityRenderer;
import com.maplecraft.entity.boss.zakum.BossZakumRightHandEntityRenderer;
import com.maplecraft.entity.mobs.BlueSnailEntity.BlueSnailEntity;
import com.maplecraft.entity.mobs.MapleMobEntityRenderer;
import com.maplecraft.entity.summon.holyDragon.HolyDragonEntityRenderer;
import com.maplecraft.MapleCraftMod;
import com.maplecraft.client.renderer.*;
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

        event.registerEntityRenderer(EntitiesInit.ARROW_FOR_BOW_ENTITY.get(), MapleArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BRONZE_ARROW_FOR_BOW_ENTITY.get(), MapleArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.STEEL_ARROW_FOR_BOW_ENTITY.get(), MapleArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.DIAMOND_ARROW_FOR_BOW_ENTITY.get(), MapleArrowRenderer::new);

        event.registerEntityRenderer(EntitiesInit.BOMB_ARROW_ENTITY.get(), MapleArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.HOLY_ARROW_ENTITY.get(), MapleArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.FIRE_ARROW_ENTITY.get(), MapleArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.POISON_BRACE_ENTITY.get(), PoisonBraceRenderer::new);
        event.registerEntityRenderer(EntitiesInit.AVENGER_ENTITY.get(), AvengerRenderer::new);

        event.registerEntityRenderer(EntitiesInit.ELEMENT_COMPOSITION_IL_ENTITY.get(), MapleArrowRenderer::new);
        event.registerEntityRenderer(EntitiesInit.ELEMENT_COMPOSITION_FP_ENTITY.get(), MapleArrowRenderer::new);

        event.registerEntityRenderer(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY.get(), BossZakumBodyEntityRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BOSS_ZAKUM_LEFT_HAND_ENTITY.get(), BossZakumLeftHandEntityRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BOSS_ZAKUM_RIGHT_HAND_ENTITY.get(), BossZakumRightHandEntityRenderer::new);

        event.registerEntityRenderer(EntitiesInit.HOLY_DRAGON_ENTITY.get(), HolyDragonEntityRenderer::new);
        event.registerEntityRenderer(EntitiesInit.BLUE_SNAIL_ENTITY.get(), MapleMobEntityRenderer<BlueSnailEntity>::new);
    }
}