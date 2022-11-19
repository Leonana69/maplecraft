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
    public BaseEquipData baseEquipData = new BaseEquipData();
    protected String armorTexture;

    public MapleArmorItem(String name, int durability, int armor, EquipCategory ec, BonusStats bs, Supplier<Ingredient> repairIngredient) {
        super(new MapleArmorMaterials(
                name,
                armor,
                durability,
                repairIngredient
        ), categoryToSlot(ec), new Properties().tab(TabsInit.TAB_MAPLE_CRAFT));
        baseEquipData.category = ec;
        baseEquipData.baseStats = bs;
        armorTexture = "maplecraft:textures/custom_models/" + name + ".png";
    }

    public static EquipmentSlot categoryToSlot(EquipCategory ec) {
        EquipmentSlot slot;
        switch (ec) {
            case HELMET -> slot = EquipmentSlot.HEAD;
            case CHEST_PLATE -> slot = EquipmentSlot.CHEST;
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
        return getEquipWiseData(itemstack).rarity != MapleRarity.COMMON;
    }

    @Override
    public MapleRarity getPotentialRarity(ItemStack itemstack) {
        return getEquipWiseData(itemstack).rarity;
    }

    @Override
    public void setPotential(ItemStack itemstack, MapleRarity rarity, PotentialType[] potentialTypes) {
        getEquipWiseData(itemstack).rarity = rarity;
        getEquipWiseData(itemstack).potentials = potentialTypes;
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
        return EquipWiseData.fromString(getEquipWiseData(itemstack).tooltip);
    }
}
