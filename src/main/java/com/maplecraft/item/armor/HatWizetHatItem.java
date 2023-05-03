package com.maplecraft.item.armor;

import com.maplecraft.client.model.WizetHatEntityModel;
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

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import static com.maplecraft.utils.AllArmorEquipKeyValues.HAT_WIZET_HAT_KV;

public class HatWizetHatItem extends MapleArmorItem {
    public static String itemName = "hat_wizet_hat";
    public HatWizetHatItem() {
        super(itemName,
                new EquipBaseData().category(EquipCategory.HAT)
                        .levelReq(HAT_WIZET_HAT_KV.levelReq)
                        .addStat("ARMOR", HAT_WIZET_HAT_KV.armor)
                        .addStat("MAX HP", HAT_WIZET_HAT_KV.maxHP)
                        .addStat("STATS", HAT_WIZET_HAT_KV.stats)
                        .addStat("SPEED", HAT_WIZET_HAT_KV.speed)
                        .addStat("JUMP", HAT_WIZET_HAT_KV.jump),
                Ingredient.of(ItemsInit.ETC_ADVANCED_MONSTER_CRYSTAL.get()));
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("head",
                                new WizetHatEntityModel<>(
                                        Minecraft.getInstance().getEntityModels().bakeLayer(WizetHatEntityModel.LAYER_LOCATION)).Head,
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
