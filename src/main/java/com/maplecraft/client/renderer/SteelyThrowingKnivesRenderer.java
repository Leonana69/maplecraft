package com.maplecraft.client.renderer;

import com.maplecraft.entity.projectile.SteelyThrowingKnivesEntity;
import com.maplecraft.client.model.SteelyThrowingKnivesEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SteelyThrowingKnivesRenderer extends ProjectileRenderer<SteelyThrowingKnivesEntity> {
    public SteelyThrowingKnivesRenderer(EntityRendererProvider.Context context) {
        super(context, new SteelyThrowingKnivesEntityModel<>(context.bakeLayer(SteelyThrowingKnivesEntityModel.LAYER_LOCATION)));
        this.scale = 0.3F;
    }
}
