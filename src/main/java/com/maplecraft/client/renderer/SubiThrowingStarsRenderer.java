package com.maplecraft.client.renderer;

import com.maplecraft.entity.projectile.SubiThrowingStarsEntity;
import com.maplecraft.client.model.SubiThrowingStarsEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SubiThrowingStarsRenderer extends ProjectileRenderer<SubiThrowingStarsEntity> {
    public SubiThrowingStarsRenderer(EntityRendererProvider.Context context) {
        super(context, new SubiThrowingStarsEntityModel<>(context.bakeLayer(SubiThrowingStarsEntityModel.LAYER_LOCATION)));
        this.scale = 0.3F;
    }
}
