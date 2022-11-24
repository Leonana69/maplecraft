package net.maplecraft.client.renderer;

import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entities.BronzeArrowForBowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BronzeArrowForBowRenderer extends ProjectileRenderer<BronzeArrowForBowEntity> {
    public BronzeArrowForBowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
