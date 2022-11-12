package dev.maplecraft.items;

import dev.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class GarnierClawWeaponItem extends ClawWeaponItem {
    public GarnierClawWeaponItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).durability(0));
        this.damage = 1.0F;
        this.power = 2.0F;
        this.accuracy = 2.0F;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("Basic claw"));
    }
}