package net.maplecraft.procedures;

import net.maplecraft.world.loot.LootItemTagCondition;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.predicates.ValueCheckCondition;

public class AddLootItemCondition {
    public static final LootItemConditionType BLOCK_TAG = register("block_tag", new LootItemTagCondition.Serializer());
    private static LootItemConditionType register(String p_81832_, Serializer<? extends LootItemCondition> p_81833_) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(p_81832_), new LootItemConditionType(p_81833_));
    }
}
