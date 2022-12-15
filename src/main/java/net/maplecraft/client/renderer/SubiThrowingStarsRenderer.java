package net.maplecraft.client.renderer;

import net.maplecraft.client.model.SubiThrowingStarsEntityModel;
import net.maplecraft.entity.projectile.SubiThrowingStarsEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SubiThrowingStarsRenderer extends ProjectileRenderer<SubiThrowingStarsEntity> {
    public SubiThrowingStarsRenderer(EntityRendererProvider.Context context) {
        super(context, new SubiThrowingStarsEntityModel<>(context.bakeLayer(SubiThrowingStarsEntityModel.LAYER_LOCATION)));
        this.scale = 0.3F;
    }
}
