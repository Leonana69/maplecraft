package com.maplecraft.entity.mobs;

import com.maplecraft.MapleCraftMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MapleMobEntityRenderer<T extends MapleMobEntity> extends GeoEntityRenderer<T> {
    public MapleMobEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MapleMobEntityModel<T>());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/" + entity.entityName + ".png");
    }
}
