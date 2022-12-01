package net.maplecraft.entities.boss.zakum;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.maplecraft.MapleCraftMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BossZakumRightHandEntityRenderer extends GeoEntityRenderer<BossZakumRightHandEntity> {
    public BossZakumRightHandEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BossZakumRightHandEntityModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(BossZakumRightHandEntity entity) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/boss_zakum_hand_entity.png");
    }

    @Override
    public RenderType getRenderType(BossZakumRightHandEntity entity, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        float scale = 1.0f;
        poseStack.scale(scale, scale, scale);

        int index = 0;
        if (entity.getCustomName() != null) {
            index = Integer.parseInt(entity.getCustomName().getString()) - 4;
        }
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(20 - 20 * index));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(30 - 15 * index));
        return super.getRenderType(entity, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
