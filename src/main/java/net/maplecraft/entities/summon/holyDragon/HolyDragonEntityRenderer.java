package net.maplecraft.entities.summon.holyDragon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.maplecraft.MapleCraftMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static net.maplecraft.entities.boss.zakum.BossZakumSpawnEggItem.zakumEntityScale;

public class HolyDragonEntityRenderer extends GeoEntityRenderer<HolyDragonEntity> {
    public HolyDragonEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HolyDragonEntityModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(HolyDragonEntity entity) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/holy_dragon_entity.png");
    }

    @Override
    public RenderType getRenderType(HolyDragonEntity entity, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        float scale = zakumEntityScale;
        poseStack.scale(scale, scale, scale);
        return super.getRenderType(entity, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
