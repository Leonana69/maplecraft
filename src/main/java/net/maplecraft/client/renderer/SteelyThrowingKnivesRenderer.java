package net.maplecraft.client.renderer;

import net.maplecraft.client.model.SteelyThrowingKnivesEntityModel;
import net.maplecraft.entities.SteelyThrowingKnivesEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SteelyThrowingKnivesRenderer extends ProjectileRenderer<SteelyThrowingKnivesEntity> {
    public SteelyThrowingKnivesRenderer(EntityRendererProvider.Context context) {
        super(context, new SteelyThrowingKnivesEntityModel<>(context.bakeLayer(SteelyThrowingKnivesEntityModel.LAYER_LOCATION)));
        this.scale = 0.3F;
    }
}
