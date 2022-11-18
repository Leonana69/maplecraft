package net.maplecraft.init;

import net.maplecraft.network.EquipCapabilitiesProvider;
import net.maplecraft.utils.EquipWiseData;
import net.maplecraft.utils.IBaseEquip;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CapabilitiesInit {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(EquipWiseData.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof IBaseEquip e) {
            event.addCapability(new ResourceLocation("maplecraft", "equip_capabilities"), new EquipCapabilitiesProvider());
        }
    }
}
