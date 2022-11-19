package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import java.util.List;
import java.util.function.Supplier;

public class MapleArmorItem extends ArmorItem implements IBaseEquip {
    public EquipBaseData baseEquipData;
    protected String armorTexture;

    public MapleArmorItem(String name, int durability, EquipBaseData b, Supplier<Ingredient> repairIngredient) {
        super(new MapleArmorMaterials(
                name,
                b.baseStats.get("ARMOR"),
                durability,
                repairIngredient
        ), categoryToSlot(b.category), new Properties().tab(TabsInit.TAB_MAPLE_CRAFT));
        baseEquipData = b;
        armorTexture = "maplecraft:textures/custom_models/" + name + ".png";
    }

    public static EquipmentSlot categoryToSlot(EquipCategory ec) {
        EquipmentSlot slot;
        switch (ec) {
            case HELMET -> slot = EquipmentSlot.HEAD;
            case CHEST -> slot = EquipmentSlot.CHEST;
            case LEGGINGS -> slot = EquipmentSlot.LEGS;
            case BOOTS -> slot = EquipmentSlot.FEET;
            default -> slot = EquipmentSlot.MAINHAND;
        }
        return slot;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return armorTexture;
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
    public MapleRarity getPotentialRarity(ItemStack itemstack) {
        return getEquipWiseData(itemstack).equipRarity;
    }

    @Override
    public EquipBaseData getBaseEquipData() {
        return baseEquipData;
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
    public List<Component> getTooltip(ItemStack itemstack) {
        return EquipWiseData.componentFromString(getEquipWiseData(itemstack).tooltip);
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }
}
