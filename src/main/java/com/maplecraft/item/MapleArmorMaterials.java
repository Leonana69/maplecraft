package com.maplecraft.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class MapleArmorMaterials implements ArmorMaterial {
    private final String name;
    private final int durability;
    private final int defense;
    private final int enchantmentValue;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairIngredient;

    MapleArmorMaterials(String name, int armor, int durability, Ingredient repairIngredient) {
        this.name = name;
        this.defense = armor;
        this.toughness = 0;
        this.knockbackResistance = 0;
        this.enchantmentValue = 0;
        this.durability = durability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot p_40410_) {
        return this.durability;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot p_40411_) {
        return this.defense;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
