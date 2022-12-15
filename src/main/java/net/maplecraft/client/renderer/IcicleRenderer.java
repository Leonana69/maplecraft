package net.maplecraft.client.renderer;

import net.maplecraft.client.model.IcicleEntityModel;
import net.maplecraft.entity.projectile.IcicleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class IcicleRenderer extends ProjectileRenderer<IcicleEntity> {
    public IcicleRenderer(EntityRendererProvider.Context context) {
        super(context, new IcicleEntityModel<>(context.bakeLayer(IcicleEntityModel.LAYER_LOCATION)));
        this.scale = 0.2F;
    }
}
