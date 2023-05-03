package com.maplecraft.network;

import com.maplecraft.procedures.SkillEffectRender;
import com.maplecraft.utils.SkillEffectInstance;
import com.maplecraft.MapleCraftMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkillEffectMessageHandler {
    SkillEffectInstance instance;

    public SkillEffectMessageHandler(SkillEffectInstance instance) {
        this.instance = instance;
    }

    public SkillEffectMessageHandler(FriendlyByteBuf buffer) {
        this.instance = new SkillEffectInstance();
        this.instance.skillName = buffer.readUtf();
        this.instance.animeCount = buffer.readInt();
        this.instance.delay = buffer.readInt();
        this.instance.hitEffectOnHit = buffer.readBoolean();
        this.instance.tickPerFrame = buffer.readFloat();
        this.instance.textureWidth = buffer.readInt();
        this.instance.textureHeight = buffer.readInt();
        this.instance.fixedPosition = buffer.readBoolean();
        this.instance.renderPos = new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
    }

    public static void buffer(SkillEffectMessageHandler message, FriendlyByteBuf buffer) {
        buffer.writeUtf(message.instance.skillName);
        buffer.writeInt(message.instance.animeCount);
        buffer.writeInt(message.instance.delay);
        buffer.writeBoolean(message.instance.hitEffectOnHit);
        buffer.writeFloat(message.instance.tickPerFrame);
        buffer.writeInt(message.instance.textureWidth);
        buffer.writeInt(message.instance.textureHeight);
        buffer.writeBoolean(message.instance.fixedPosition);
        buffer.writeDouble(message.instance.renderPos.x);
        buffer.writeDouble(message.instance.renderPos.y);
        buffer.writeDouble(message.instance.renderPos.z);
    }

    public static void handler(SkillEffectMessageHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            onMessageReceived(message.instance);
        });
        context.setPacketHandled(true);
    }

    public static void onMessageReceived(SkillEffectInstance instance) {
        SkillEffectRender.hitEffectList.add(instance);
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(SkillEffectMessageHandler.class, SkillEffectMessageHandler::buffer, SkillEffectMessageHandler::new, SkillEffectMessageHandler::handler);
    }
}
