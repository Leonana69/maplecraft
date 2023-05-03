package com.maplecraft.item;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.IBaseEquip;
import com.maplecraft.init.TabsInit;
import com.maplecraft.utils.EquipCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import java.util.List;

public class    MapleArmorItem extends ArmorItem implements IBaseEquip {
    public EquipBaseData baseEquipData;
    protected String armorTexture;

    public MapleArmorItem(String name, EquipBaseData data, Ingredient repairIngredient) {
        super(new MapleArmorMaterials(
                name,
                data.baseStats.get("ARMOR"),
                data.levelReq * EquipBaseData.durationPerLevel + EquipBaseData.durationBase,
                repairIngredient
        ), categoryToSlot(data.category), new Properties().tab(TabsInit.TAB_MAPLE_CRAFT));
        baseEquipData = data;
        armorTexture = "maplecraft:textures/armors/" + name + ".png";
    }

    public static EquipmentSlot categoryToSlot(EquipCategory ec) {
        EquipmentSlot slot;
        switch (ec) {
            case HAT -> slot = EquipmentSlot.HEAD;
            case TOP -> slot = EquipmentSlot.CHEST;
            case BOTTOM -> slot = EquipmentSlot.LEGS;
            case SHOES -> slot = EquipmentSlot.FEET;
            default -> slot = EquipmentSlot.MAINHAND;
        }
        return slot;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return armorTexture;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, list, flag);
        appendHoverText(itemStack, level, list, baseEquipData);
    }

    @Override
    public EquipBaseData getBaseEquipData() {
        return baseEquipData;
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }
}
