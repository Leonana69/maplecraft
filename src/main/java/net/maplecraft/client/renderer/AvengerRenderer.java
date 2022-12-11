package net.maplecraft.client.renderer;

import net.maplecraft.client.model.AvengerEntityModel;
import net.maplecraft.entities.AvengerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class AvengerRenderer extends ProjectileRenderer<AvengerEntity> {
    public AvengerRenderer(EntityRendererProvider.Context context) {
        super(context, new AvengerEntityModel<>(context.bakeLayer(AvengerEntityModel.LAYER_LOCATION)));
    }
}

