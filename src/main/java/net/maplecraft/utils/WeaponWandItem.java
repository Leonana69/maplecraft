package net.maplecraft.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class WeaponWandItem extends WeaponItem {
    public WeaponWandItem(EquipBaseData data) {
        super(data.category(EquipCategory.WAND));
    }
}
