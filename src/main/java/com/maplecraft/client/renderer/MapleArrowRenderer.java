package com.maplecraft.client.renderer;

import com.maplecraft.client.model.ArrowForBowEntityModel;
import com.maplecraft.entity.MapleProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MapleArrowRenderer extends ProjectileRenderer<MapleProjectileEntity> {
    public MapleArrowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
