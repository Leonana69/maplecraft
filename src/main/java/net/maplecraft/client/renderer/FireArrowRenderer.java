package net.maplecraft.client.renderer;

import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entities.FireArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class FireArrowRenderer extends ProjectileRenderer<FireArrowEntity> {
    public FireArrowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
