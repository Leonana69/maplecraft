package com.maplecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.maplecraft.MapleCraftMod;
import com.maplecraft.entity.MapleProjectileEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class ProjectileRenderer<T extends MapleProjectileEntity> extends EntityRenderer<T> {
    private ResourceLocation TEXTURE = null;
    private final EntityModel<T> model;
    protected float scale = 0.8F;

    public ProjectileRenderer(EntityRendererProvider.Context context, EntityModel<T> model) {
        super(context);
        this.model = model;
    }

    @Override
    public void render(@NotNull T entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
        model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1.0F);
        poseStack.popPose();
        super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
        TEXTURE = null;
    }

    @Override
    public ResourceLocation getTextureLocation(@NotNull T entityIn) {
        if (entityIn.isRotate()) {
            TEXTURE = new ResourceLocation(MapleCraftMod.MODID, "textures/entities/" + entityIn.getProjectileName() + "_rotate_entity.png");
        } else {
            TEXTURE = new ResourceLocation(MapleCraftMod.MODID, "textures/entities/" + entityIn.getProjectileName() + "_entity.png");
        }
        return TEXTURE;
    }
}