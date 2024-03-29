package com.maplecraft.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.maplecraft.MapleCraftMod;
import com.maplecraft.utils.SkillEffectInstance;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.opengl.GL11;

public class SkillEffectRenderer {
    public static void renderInWorld(float partialTick, PoseStack matrix, Camera camera, SkillEffectInstance instance) {
        if (camera == null) {
            camera = Minecraft.getInstance().getEntityRenderDispatcher().camera;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE,
                GL11.GL_ZERO);

        Vec3 camPos = camera.getPosition();
        double camX = camPos.x();
        double camY = camPos.y();
        double camZ = camPos.z();


        float textureHeight = instance.textureHeight;
        float textureWidth = instance.textureWidth;
        float u1 = instance.currentAnime / (float) instance.animeCount;
        float u2 = (instance.currentAnime + 1) / (float) instance.animeCount;
        float scaleToGui = 0.02f;

        //  boolean sneaking = entity.isCrouching();
        //  float height = entity.getBbHeight() + 0.6F - (sneaking ? 0.25F : 0.0F);

        float height = textureHeight * scaleToGui;

        matrix.pushPose();
        matrix.translate(instance.renderPos.x - camX, (instance.renderPos.y + height) - camY, instance.renderPos.z - camZ);
        matrix.mulPose(Vector3f.YP.rotationDegrees(-camera.getYRot()));
        // matrix.mulPose(Vector3f.XP.rotationDegrees(camera.getXRot()));
        matrix.scale(-scaleToGui, -scaleToGui, scaleToGui);

        Matrix4f m4f = matrix.last().pose();
        String textureName = "textures/skill/" + instance.skillName + (instance.hitEffectOnHit ? "_hit.png" : ".png");

        RenderSystem.setShaderTexture(0,
                new ResourceLocation(MapleCraftMod.MODID, textureName));

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        buffer.vertex(m4f, -textureWidth / 2, 0, 0)
                .uv(u1, 0).endVertex();
        buffer.vertex(m4f, -textureWidth / 2, textureHeight, 0)
                .uv(u1, 1).endVertex();
        buffer.vertex(m4f, textureWidth / 2, textureHeight, 0)
                .uv(u2, 1).endVertex();
        buffer.vertex(m4f, textureWidth / 2, 0, 0)
                .uv(u2, 0).endVertex();
        tesselator.end();

        matrix.popPose();

        RenderSystem.disableBlend();
    }
//    public static void renderSkillEffect(PoseStack matrix, LivingEntity entity, Camera camera, Vec3 renderPos) {
//        Vec3 camPos = camera.getPosition();
//        double camX = camPos.x();
//        double camY = camPos.y();
//        double camZ = camPos.z();
//
//        matrix.pushPose();
//        matrix.translate(renderPos.x - camX, renderPos.y - camY, renderPos.z - camZ);
//        // matrix.mulPose(Vector3f.XP.rotationDegrees(camera.getXRot()));
//        matrix.mulPose(Vector3f.YP.rotationDegrees(-camera.getYRot()));
//
//        Matrix4f m4f = matrix.last().pose();
//
//        float textureHeight = 219F;
//        float textureWidth = 68F;
//
//        RenderSystem.setShaderColor(1, 1, 1, 1);
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderTexture(0, TEXTURES);
//        RenderSystem.enableBlend();
//
//        float half = textureWidth / 2;
//
//        Tesselator tesselator = Tesselator.getInstance();
//        BufferBuilder buffer = tesselator.getBuilder();
//        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
//
//        buffer.vertex(m4f, -half, 0, 0)
//                .uv(0, 0).endVertex();
//        buffer.vertex(m4f, -half, textureHeight, 0)
//                .uv(0, 1).endVertex();
//        buffer.vertex(m4f, (-half + textureWidth), textureHeight, 0)
//                .uv(1, 1).endVertex();
//        buffer.vertex(m4f, (-half + textureWidth), 0, 0)
//                .uv(1, 0).endVertex();
//        tesselator.end();
//    }
}
