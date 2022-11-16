package net.maplecraft.procedures;

import net.maplecraft.utils.BaseEquipInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;


@OnlyIn(Dist.CLIENT)
public class VanillaTooltipRemover {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new VanillaTooltipRemover());
    }

    @SubscribeEvent
    public void onRenderTooltip(RenderTooltipEvent.Pre event) {
        ItemStack itemStack = event.getItemStack();
        // if the item is our BaseEquipItem and the tooltip contains vanilla elements
        if (itemStack.getItem() instanceof BaseEquipInterface equipItem && equipItem.getTooltip().size() != event.getComponents().size()) {
            event.setCanceled(true);
            // fire a new event with custom tooltip only
            assert Minecraft.getInstance().screen != null;
            Minecraft.getInstance().screen.renderComponentTooltip(event.getPoseStack(), equipItem.getTooltip(), event.getX(), event.getY());
        }
    }
}
