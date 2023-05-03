package com.maplecraft.item.armor;

import com.maplecraft.client.model.RedHeadbandHatEntityModel;
import com.maplecraft.init.ItemsInit;
import com.maplecraft.item.MapleArmorItem;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.EquipCategory;
import com.maplecraft.utils.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import static com.maplecraft.utils.AllArmorEquipKeyValues.HAT_RED_HEADBAND_KV;

public class HatRedHeadbandItem extends MapleArmorItem {
    public static String itemName = "hat_red_headband";
    public HatRedHeadbandItem() {
        super(itemName,
                new EquipBaseData().category(EquipCategory.HAT)
                        .levelReq(HAT_RED_HEADBAND_KV.levelReq)
                        .addStat("ARMOR", HAT_RED_HEADBAND_KV.armor)
                        .addStat("MAX HP", HAT_RED_HEADBAND_KV.maxHP)
                        .addStat("STATS", HAT_RED_HEADBAND_KV.stats)
                        .addStat("SPEED", HAT_RED_HEADBAND_KV.speed)
                        .addStat("JUMP", HAT_RED_HEADBAND_KV.jump),
                Ingredient.of(ItemsInit.ETC_BASIC_MONSTER_CRYSTAL.get()));
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("head",
                                new RedHeadbandHatEntityModel(
                                        Minecraft.getInstance().getEntityModels().bakeLayer(RedHeadbandHatEntityModel.LAYER_LOCATION)).Head,
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }
}
