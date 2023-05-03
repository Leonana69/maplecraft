package com.maplecraft.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.maplecraft.item.etc.EtcAdvancedMonsterCrystalItem;
import com.maplecraft.item.etc.EtcBasicMonsterCrystalItem;
import com.maplecraft.item.etc.EtcIntermediateMonsterCrystalItem;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.IBaseEquip;
import com.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class WeaponItem extends Item implements IBaseEquip {
    public EquipBaseData baseEquipData;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public WeaponItem(EquipBaseData data) {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                .durability(data.levelReq * EquipBaseData.durationPerLevel + EquipBaseData.durationBase));
        this.baseEquipData = data;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        double as = data.baseStats.get("ATTACK_SPEED");
        as = 1 / (0.25 + as * 0.175) - 4.0;
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", as, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
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

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean isValidRepairItem(ItemStack weapon, ItemStack ingredient) {
        switch (baseEquipData.levelReq) {
            case 0, 5 -> {
                return ingredient.getItem() instanceof EtcBasicMonsterCrystalItem;
            }
            case 10 -> {
                return ingredient.getItem() instanceof EtcIntermediateMonsterCrystalItem;
            }
            case 15 -> {
                return ingredient.getItem() instanceof EtcAdvancedMonsterCrystalItem;
            }
        }
        return false;
    }
}
