package com.maplecraft.client.renderer;

import com.maplecraft.client.model.MagicBallEntityModel;
import com.maplecraft.entity.projectile.PoisonBraceEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class PoisonBraceRenderer extends ProjectileRenderer<PoisonBraceEntity> {
    public PoisonBraceRenderer(EntityRendererProvider.Context context) {
        super(context, new MagicBallEntityModel<>(context.bakeLayer(MagicBallEntityModel.LAYER_LOCATION)));
    }
}
