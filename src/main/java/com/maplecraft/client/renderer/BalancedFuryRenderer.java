package com.maplecraft.client.renderer;

import com.maplecraft.entity.projectile.BalancedFuryEntity;
import com.maplecraft.client.model.BalancedFuryEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BalancedFuryRenderer extends ProjectileRenderer<BalancedFuryEntity> {
    public BalancedFuryRenderer(EntityRendererProvider.Context context) {
        super(context, new BalancedFuryEntityModel<>(context.bakeLayer(BalancedFuryEntityModel.LAYER_LOCATION)));
        this.scale = 0.2F;
    }
}