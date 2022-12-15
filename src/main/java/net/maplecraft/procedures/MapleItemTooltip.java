package net.maplecraft.procedures;

import net.maplecraft.utils.IBaseEquip;
import net.maplecraft.item.MapleItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

@OnlyIn(Dist.CLIENT)
public class MapleItemTooltip {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new MapleItemTooltip());
    }

    @SubscribeEvent
    public void onRenderTooltip(RenderTooltipEvent.Pre event) {
        ItemStack itemStack = event.getItemStack();
        // if the item is our equip and the tooltip has nothing added by vanilla minecraft
        // the second condition is essential, otherwise this event will be fired infinitely
        if (itemStack.getItem() instanceof IBaseEquip equipItem && event.getComponents().size() != equipItem.getTooltip(itemStack).size()) {
            event.setCanceled(true);
            // fire a new event with custom tooltip only
            assert Minecraft.getInstance().screen != null;
            Minecraft.getInstance().screen.renderComponentTooltip(event.getPoseStack(), equipItem.getTooltip(itemStack), event.getX(), event.getY());
        }
    }

    @SubscribeEvent
    public void onRenderToolTipColor(RenderTooltipEvent.Color event) {
        ItemStack itemStack = event.getItemStack();
        Integer color = null;
        if (itemStack.getItem() instanceof IBaseEquip equipItem) {
            color = equipItem.getPotentialRarity(itemStack).color.getColor();
        } else if (itemStack.getItem() instanceof MapleItem item) {
            color = item.rarity.color.getColor();
        }
        if (color != null) {
            event.setBorderStart(0xFF000000 | color);
            event.setBorderEnd(0xFF000000 | color);
            event.setBackground(0xCC000000);
        }
    }
}
