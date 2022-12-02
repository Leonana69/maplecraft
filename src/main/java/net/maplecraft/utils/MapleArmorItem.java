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

public class    MapleArmorItem extends ArmorItem implements IBaseEquip {
    public EquipBaseData baseEquipData;
    protected String armorTexture;

    public MapleArmorItem(String name, EquipBaseData data, Supplier<Ingredient> repairIngredient) {
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
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, world, list, flag);
        appendHoverText(itemStack, list, baseEquipData);
    }

    @Override
    public EquipBaseData getBaseEquipData() {
        return baseEquipData;
    }

    @Override
    public EquipCategory getCategory() {
        return baseEquipData.category;
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }
}
