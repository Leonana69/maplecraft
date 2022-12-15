package net.maplecraft.client.renderer;

import net.maplecraft.client.model.ArrowForBowEntityModel;
import net.maplecraft.entity.MapleProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MapleArrowRenderer extends ProjectileRenderer<MapleProjectileEntity> {
    public MapleArrowRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowForBowEntityModel<>(context.bakeLayer(ArrowForBowEntityModel.LAYER_LOCATION)));
    }
}
