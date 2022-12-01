package net.maplecraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.maplecraft.MapleCraftMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ZakumHelmetEntityModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MapleCraftMod.MODID, "zakum_helmet_entity_model"), "main");
	public final ModelPart Head;

	public ZakumHelmetEntityModel(ModelPart root) {
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
				.texOffs(24, 11).addBox(-3.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 9).addBox(2.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 2).addBox(2.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 23).addBox(-0.5F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 18).addBox(-3.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(31, 20).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(11, 18).addBox(-4.0F, -10.0F, -5.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 0).addBox(-5.0F, -10.0F, 4.0F, 10.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(11, 23).addBox(-4.0F, -14.0F, 3.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(38, 38).addBox(-1.0F, -14.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(31, 23).addBox(-3.0F, -12.0F, 4.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 38).addBox(-2.0F, -13.0F, 4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 38).addBox(-2.0F, -13.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -11.0F, -5.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(24, 12).addBox(-3.0F, -12.0F, -5.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(31, 17).addBox(-5.0F, -12.0F, 3.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(20, 18).addBox(4.0F, -10.0F, -5.0F, 1.0F, 11.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(14, 38).addBox(-3.0F, -15.0F, 3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).addBox(-1.0F, -16.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(-5.0F, -10.0F, -5.0F, 1.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right = Head.addOrReplaceChild("right", CubeListBuilder.create().texOffs(3, 21).addBox(-13.0F, -7.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-13.0F, -10.0F, 2.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(-9.0F, -12.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 4).addBox(-11.0F, -7.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-10.0F, -11.0F, 3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(18.0F, 0.0F, 0.0F));

		PartDefinition left = Head.addOrReplaceChild("left", CubeListBuilder.create().texOffs(24, 0).addBox(-7.0F, -7.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(-8.0F, -10.0F, 2.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 22).addBox(-10.0F, -12.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 6).addBox(-8.0F, -7.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 9).addBox(-9.0F, -11.0F, 3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}