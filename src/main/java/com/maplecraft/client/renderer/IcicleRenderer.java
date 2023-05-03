package com.maplecraft.client.renderer;

import com.maplecraft.client.model.IcicleEntityModel;
import com.maplecraft.entity.projectile.IcicleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class IcicleRenderer extends ProjectileRenderer<IcicleEntity> {
    public IcicleRenderer(EntityRendererProvider.Context context) {
        super(context, new IcicleEntityModel<>(context.bakeLayer(IcicleEntityModel.LAYER_LOCATION)));
        this.scale = 0.2F;
    }
}
