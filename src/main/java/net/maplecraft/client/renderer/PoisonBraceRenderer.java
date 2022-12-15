package net.maplecraft.client.renderer;

import net.maplecraft.client.model.MagicBallEntityModel;
import net.maplecraft.entity.projectile.PoisonBraceEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class PoisonBraceRenderer extends ProjectileRenderer<PoisonBraceEntity> {
    public PoisonBraceRenderer(EntityRendererProvider.Context context) {
        super(context, new MagicBallEntityModel<>(context.bakeLayer(MagicBallEntityModel.LAYER_LOCATION)));
    }
}
