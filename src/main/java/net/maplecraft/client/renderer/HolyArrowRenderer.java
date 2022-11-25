package net.maplecraft.client.renderer;

import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entities.HolyArrowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HolyArrowRenderer extends ProjectileRenderer<HolyArrowEntity> {
    public HolyArrowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
