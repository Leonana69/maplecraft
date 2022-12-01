package net.maplecraft.entities.boss.zakum;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.maplecraft.MapleCraftMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BossZakumBodyEntityRenderer extends GeoEntityRenderer<BossZakumBodyEntity> {
    public BossZakumBodyEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BossZakumBodyEntityModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(BossZakumBodyEntity entity) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/boss_zakum_body_entity.png");
    }

    @Override
    public RenderType getRenderType(BossZakumBodyEntity entity, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        float scale = 1.0f;
        poseStack.scale(scale, scale, scale);
        return super.getRenderType(entity, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
