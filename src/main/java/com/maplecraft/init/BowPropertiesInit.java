package com.maplecraft.init;

import com.maplecraft.item.WeaponBowItem;
import com.maplecraft.item.WeaponCrossbowItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BowPropertiesInit {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        makeBow(ItemsInit.BOW_WAR_BOW.get());
        makeBow(ItemsInit.BOW_HUNTERS_BOW.get());
        makeBow(ItemsInit.BOW_RYDEN.get());
        makeBow(ItemsInit.BOW_MAPLE_BOW.get());

        makeCrossbow(ItemsInit.CROSSBOW_CROSSBOW.get());
        makeCrossbow(ItemsInit.CROSSBOW_BALANCHE.get());
        makeCrossbow(ItemsInit.CROSSBOW_GOLDEN_RAVEN.get());
        makeCrossbow(ItemsInit.CROSSBOW_MAPLE_CROSSBOW.get());
    }

    private static void makeBow(Item item) {
        ItemProperties.register(item,
                new ResourceLocation("pull"),
                (itemStack, p_174636_, livingEntity, p_174638_) -> {
                        if (livingEntity != null) {
                            if (itemStack.getItem() instanceof WeaponBowItem bow && bow.setUsingAnime) {
                                return 1.0F;
                            }
                            if (livingEntity.getUseItem() != itemStack) {
                                return 0.0F;
                            }
                            return (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0f;
                        }
                        return 0.0F;
                 });
        ItemProperties.register(item,
                new ResourceLocation("pulling"),
                (itemStack, p_174631_, livingEntity, p_174633_) -> {
                    if (livingEntity != null) {
                        if (livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack)
                            return 1.0F;
                        else if (livingEntity.getMainHandItem().getItem() instanceof WeaponBowItem bow && bow.setUsingAnime)
                            return 1.0F;
                    }
                    return 0.0F;
                });
    }

    private static void makeCrossbow(Item item) {
        ItemProperties.register(item,
                new ResourceLocation("pull"),
                (itemStack, p_174636_, livingEntity, p_174638_) -> {
                    if (livingEntity != null) {
                        if (livingEntity.getUseItem() != itemStack) {
                            return 0.0F;
                        }
                        return (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0f;
                    }
                    return 0.0F;
                });
        ItemProperties.register(item,
                new ResourceLocation("pulling"),
                (itemStack, p_174631_, livingEntity, p_174633_) -> {
                    if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack) {
                        return 1.0F;
                    }
                    return 0.0F;
                });
        ItemProperties.register(item,
                new ResourceLocation("charged"),
                (itemStack, p_174631_, livingEntity, p_174633_) -> (livingEntity != null && WeaponCrossbowItem.isCharged(itemStack))
                        || (itemStack.getItem() instanceof WeaponCrossbowItem crossbow && crossbow.setUsingAnime) ? 1.0F : 0.0F);
    }
}
