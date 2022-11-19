package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.maplecraft.network.EquipCapabilitiesProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WeaponItem extends Item implements IBaseEquip {
    public BaseEquipData baseEquipData = new BaseEquipData();

    public WeaponItem(Properties properties, EquipCategory ec, BaseStats bs) {
        super(properties.tab(TabsInit.TAB_MAPLE_CRAFT));
        baseEquipData.category = ec;
        baseEquipData.baseStats = bs;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, world, list, flag);
        appendHoverText(itemStack, list, baseEquipData);
    }

    @Override
    public boolean hasPotential(ItemStack itemstack) {
        return getEquipWiseData(itemstack).equipRarity != MapleRarity.COMMON;
    }

    @Override
    public List<Component> getTooltip(ItemStack itemstack) {
        return EquipWiseData.componentFromString(getEquipWiseData(itemstack).tooltip);
    }

    @Override
    public MapleRarity getPotentialRarity(ItemStack itemstack) {
        return getEquipWiseData(itemstack).equipRarity;
    }

    @Override
    public void setPotential(ItemStack itemstack, MapleRarity rarity, PotentialStats[] potentialStats) {
        getEquipWiseData(itemstack).equipRarity = rarity;
        getEquipWiseData(itemstack).potentials = potentialStats;
    }

    @Override
    public void setStarForce(ItemStack itemstack, int starForce) {
        getEquipWiseData(itemstack).starForce = starForce;
    }

    @Override
    public EquipCategory getCategory() {
        return baseEquipData.category;
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new EquipCapabilitiesProvider();
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }
}
