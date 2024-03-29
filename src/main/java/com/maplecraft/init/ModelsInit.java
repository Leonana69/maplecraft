package com.maplecraft.init;

import com.maplecraft.client.model.*;
import com.maplecraft.client.model.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelsInit {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SubiThrowingStarsEntityModel.LAYER_LOCATION, SubiThrowingStarsEntityModel::createBodyLayer);
        event.registerLayerDefinition(IcicleEntityModel.LAYER_LOCATION, IcicleEntityModel::createBodyLayer);
        event.registerLayerDefinition(SteelyThrowingKnivesEntityModel.LAYER_LOCATION, SteelyThrowingKnivesEntityModel::createBodyLayer);
        event.registerLayerDefinition(BalancedFuryEntityModel.LAYER_LOCATION, BalancedFuryEntityModel::createBodyLayer);
        event.registerLayerDefinition(ArrowForBowEntityModel.LAYER_LOCATION, ArrowForBowEntityModel::createBodyLayer);
        event.registerLayerDefinition(MagicBallEntityModel.LAYER_LOCATION, MagicBallEntityModel::createBodyLayer);
        event.registerLayerDefinition(AvengerEntityModel.LAYER_LOCATION, AvengerEntityModel::createBodyLayer);

        event.registerLayerDefinition(RedHeadbandHatEntityModel.LAYER_LOCATION, RedHeadbandHatEntityModel::createBodyLayer);
        event.registerLayerDefinition(WizetHatEntityModel.LAYER_LOCATION, WizetHatEntityModel::createBodyLayer);
        event.registerLayerDefinition(ZakumHelmetEntityModel.LAYER_LOCATION, ZakumHelmetEntityModel::createBodyLayer);
    }
}
