package net.maplecraft.client.renderer;

import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entities.DiamondArrowForBowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DiamondArrowForBowRenderer extends ProjectileRenderer<DiamondArrowForBowEntity> {
    public DiamondArrowForBowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
