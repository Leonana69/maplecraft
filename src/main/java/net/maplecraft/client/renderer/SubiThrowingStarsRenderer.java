package net.maplecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.client.model.SubiThrowingStarsEntityModel;
import net.maplecraft.entities.SubiThrowingStarsEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SubiThrowingStarsRenderer extends EntityRenderer<SubiThrowingStarsEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MapleCraftMod.MODID, "textures/entities/subi_throwing_stars_entity.png");

    private final SubiThrowingStarsEntityModel model;
    private final float scale = 0.3F;

    public SubiThrowingStarsRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new SubiThrowingStarsEntityModel(context.bakeLayer(SubiThrowingStarsEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(SubiThrowingStarsEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn,
                       int packedLightIn) {
        VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
        model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
        poseStack.popPose();
        super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(SubiThrowingStarsEntity entity) {
        return TEXTURE;
    }
}
