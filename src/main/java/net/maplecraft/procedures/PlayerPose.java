package net.maplecraft.procedures;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerPose {
//    @SubscribeEvent
//    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
////        System.out.println("Player rendered");
//        assert event != null;
//        PlayerModel<AbstractClientPlayer> model = event.getRenderer().getModel();
//        System.out.println("Right arm xRot: " + model.rightArm.xRot
//                + ", yRot: " + model.rightArm.yRot
//                + ", zRot: " + model.rightArm.zRot);
//
//        System.out.println("Right arm x: " + model.rightArm.x
//                + ", y: " + model.rightArm.y
//                + ", z: " + model.rightArm.z);
//        model.rightArm.visible = false;
//    }
//
//    @SubscribeEvent
//    public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
//        Player player = event.getEntity();
//        PlayerRenderer render = event.getRenderer();
//        PlayerModel<AbstractClientPlayer> model = render.getModel();
//
//        if (player != null && player.getMainHandItem().getItem() instanceof UseRedPotionItem){
//            ModelPart arm = model.rightArm;
//            ModelPart body = model.body;
//            arm.visible = true;
//            arm.y = 24.0F - arm.y;
////            arm.z = -Mth.sin((float) Math.toRadians(player.renderYawOffset)) * 5.5F;
//            arm.xRot = -1.6F; // - (player.rotationPitch/90)*1.2F; //-3.0F > -1.65F > -0.0F;
////            arm.yRot =  (float) -Math.toRadians(player.renderYawOffset) + 3.2F + -Math.abs((player.getViewVector(0).x / 90)) * -0.05F;
//            arm.yRot = 0.0F;
//            arm.zRot = 0.0F;
//
//            MultiBufferSource buffer = event.getMultiBufferSource();
//
//            int texture = OverlayTexture.NO_OVERLAY;
//
//            arm.visible = true;
//
//            System.out.println("View x: " + player.getViewVector(0).x
//                    + ", y: " + player.getViewVector(0).y
//                    + ", z: " + player.getViewVector(0).z);
//            arm.render(event.getPoseStack(),
//                    buffer.getBuffer(model.renderType(((AbstractClientPlayer) player).getSkinTextureLocation())),
//                    event.getPackedLight(),
//                    texture);;
//        }
//    }
}
