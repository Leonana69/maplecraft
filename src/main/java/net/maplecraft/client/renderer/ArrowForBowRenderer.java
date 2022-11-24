package net.maplecraft.client.renderer;

import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entities.ArrowForBowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ArrowForBowRenderer extends ProjectileRenderer<ArrowForBowEntity> {
    public ArrowForBowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
