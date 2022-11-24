package net.maplecraft.client.renderer;

import net.maplecraft.client.model.BalancedFuryEntityModel;
import net.maplecraft.entities.BalancedFuryEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BalancedFuryRenderer extends ProjectileRenderer<BalancedFuryEntity> {
    public BalancedFuryRenderer(EntityRendererProvider.Context context) {
        super(context, new BalancedFuryEntityModel<>(context.bakeLayer(BalancedFuryEntityModel.LAYER_LOCATION)));
        this.scale = 0.2F;
    }
}