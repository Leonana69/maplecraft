package com.maplecraft.client.renderer;

import com.maplecraft.client.model.AvengerEntityModel;
import com.maplecraft.entity.projectile.AvengerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class AvengerRenderer extends ProjectileRenderer<AvengerEntity> {
    public AvengerRenderer(EntityRendererProvider.Context context) {
        super(context, new AvengerEntityModel<>(context.bakeLayer(AvengerEntityModel.LAYER_LOCATION)));
    }
}

