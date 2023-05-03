package com.maplecraft.item;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.EquipCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class WeaponDaggerItem extends WeaponItem {
    public WeaponDaggerItem(EquipBaseData data) {
        super(data.category(EquipCategory.DAGGER));
    }


    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity p_43279_, LivingEntity player) {
        itemStack.hurtAndBreak(1, player, (e) -> {
            e.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
}
