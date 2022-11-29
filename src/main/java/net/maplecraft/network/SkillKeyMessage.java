package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.utils.AllSkillList;
import net.maplecraft.utils.SkillItem;
import net.maplecraft.utils.WeaponBowItem;
import net.maplecraft.utils.WeaponCrossbowItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkillKeyMessage {
    int type, duration, key;

    public SkillKeyMessage(int type, int duration, int key) {
        this.type = type;
        this.duration = duration;
        this.key = key;
    }

    public SkillKeyMessage(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.duration = buffer.readInt();
        this.key = buffer.readInt();
    }

    public static void buffer(SkillKeyMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.duration);
        buffer.writeInt(message.key);
    }

    public static void handler(SkillKeyMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(context.getSender(), message.type, message.duration, message.key);
        });
        context.setPacketHandled(true);
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(SkillKeyMessage.class, SkillKeyMessage::buffer, SkillKeyMessage::new, SkillKeyMessage::handler);
    }

    public static void pressAction(Player player, int type, int duration, int keyID) {
        ItemStack weapon = player.getMainHandItem();
        if (weapon.getItem() instanceof WeaponBowItem bow) {
            bow.setUsingAnime = type == 0;
        } else if (weapon.getItem() instanceof WeaponCrossbowItem crossbow) {
            crossbow.setUsingAnime = type == 0;
        }

        if (type == 1) {
            // released
            int skillID = (int) Variables.get(player, "skillID" + (keyID - 1));
            ItemLike skillItem = AllSkillList.SKILLS.get(skillID);
            if (skillItem != null) {
                SkillItem skill = (SkillItem) skillItem.asItem();
                if (skill.canUse(player)) {
                    skill.playerEffect(player);
                    skill.skillEffect(player);
                }
            }
        }
    }
}
