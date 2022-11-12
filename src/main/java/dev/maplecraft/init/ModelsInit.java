package dev.maplecraft.init;

import dev.maplecraft.client.model.SteelyThrowingKnivesEntityModel;
import dev.maplecraft.client.model.SubiThrowingStarsEntityModel;
import dev.maplecraft.entities.SubiThrowingStarsEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelsInit {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SubiThrowingStarsEntityModel.LAYER_LOCATION, SubiThrowingStarsEntityModel::createBodyLayer);
        event.registerLayerDefinition(SteelyThrowingKnivesEntityModel.LAYER_LOCATION, SteelyThrowingKnivesEntityModel::createBodyLayer);
    }
}
