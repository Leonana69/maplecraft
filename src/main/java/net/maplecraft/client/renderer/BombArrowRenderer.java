package net.maplecraft.client.renderer;

import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entities.BombArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BombArrowRenderer extends ProjectileRenderer<BombArrowEntity> {
    public BombArrowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
