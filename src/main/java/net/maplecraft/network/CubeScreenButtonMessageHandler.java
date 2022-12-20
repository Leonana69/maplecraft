package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.inventory.CubeMenu;
import net.maplecraft.utils.IBaseEquip;
import net.maplecraft.item.CubeItem;
import net.maplecraft.item.ScrollItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CubeScreenButtonMessageHandler {
    private final int buttonID, guiType;

    public CubeScreenButtonMessageHandler(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.guiType = buffer.readInt();
    }

    public CubeScreenButtonMessageHandler(int buttonID, int guiType) {
        this.buttonID = buttonID;
        this.guiType = guiType;
    }

    public static void buffer(CubeScreenButtonMessageHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.guiType);
    }

    public static void handler(CubeScreenButtonMessageHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getSender() != null)
                handleButtonAction(context.getSender(), message.buttonID, message.guiType);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player player, int buttonID, int guiType) {
        if (!player.level.isClientSide) {
            CubeMenu cubeMenu = (CubeMenu) player.containerMenu;
            if (buttonID == 0) {
                cubeMenu.updated = false;
                Supplier supplier = (Supplier) player.containerMenu;
                if (supplier.get() instanceof Map slots) {
                    // slot one is cube or scroll
                    ItemStack itemStack0 = ((Slot) slots.get(0)).getItem();
                    ItemStack itemStack1 = ((Slot) slots.get(1)).getItem();
                    if (itemStack0.getItem() instanceof IBaseEquip baseEquip) {
                        if (itemStack1.getItem() instanceof CubeItem cube) {
                            // cube
                            cube.execute(player, itemStack0, itemStack1);
                            if (guiType == 0 && cubeMenu.updated) {
                                baseEquip.setPotential(itemStack0, cubeMenu.newRarity, cubeMenu.newPotentials);
                            }
                        } else if (itemStack1.getItem() instanceof ScrollItem scroll) {
                            scroll.execute(player, itemStack0, itemStack1);
                            if (cubeMenu.updated) {
                                baseEquip.setPotential(itemStack0, cubeMenu.newRarity, cubeMenu.newPotentials);
                            }
                        }
                    } else {
                        if (!player.level.isClientSide())
                            player.displayClientMessage(Component.translatable("utils.maplecraft.cube_missing_equip"), (false));
                    }
                }
            } else if (buttonID == 1) {
                if (player.containerMenu instanceof Supplier supplier && supplier.get() instanceof Map slots) {
                    if (((Slot) slots.get(0)).getItem().getItem() instanceof IBaseEquip baseEquip && cubeMenu.updated) {
                        baseEquip.setPotential(((Slot) slots.get(0)).getItem(), cubeMenu.newRarity, cubeMenu.newPotentials);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(CubeScreenButtonMessageHandler.class, CubeScreenButtonMessageHandler::buffer, CubeScreenButtonMessageHandler::new,
                CubeScreenButtonMessageHandler::handler);
    }
}
