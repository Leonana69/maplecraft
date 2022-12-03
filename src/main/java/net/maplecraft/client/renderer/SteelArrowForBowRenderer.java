package net.maplecraft.client.renderer;

import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entities.SteelArrowForBowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SteelArrowForBowRenderer extends ProjectileRenderer<SteelArrowForBowEntity> {
    public SteelArrowForBowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
