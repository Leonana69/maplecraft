package com.maplecraft.entity;

import com.maplecraft.MapleCraftMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
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

    @Override
    public RenderType getRenderType(T entity, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        float scale = entity.scale;
        poseStack.scale(scale, scale, scale);
        return super.getRenderType(entity, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
