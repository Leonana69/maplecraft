package net.maplecraft.world.loot;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import net.maplecraft.procedures.AddLootItemCondition;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.Set;

public class LootItemTagCondition implements LootItemCondition {
    final Block block;
    final StatePropertiesPredicate properties;

    public LootItemTagCondition(Block p_81762_, StatePropertiesPredicate p_81763_) {
        this.block = p_81762_;
        this.properties = p_81763_;
    }

    public Set<LootContextParam<?>> getReferencedContextParams() {
        return ImmutableSet.of(LootContextParams.BLOCK_STATE);
    }

    @Override
    public LootItemConditionType getType() {
        return AddLootItemCondition.BLOCK_TAG;
    }

    public boolean test(LootContext p_81772_) {
        BlockState blockstate = p_81772_.getParamOrNull(LootContextParams.BLOCK_STATE);
        return blockstate != null && blockstate.is(this.block) && this.properties.matches(blockstate);
    }

    public static LootItemTagCondition.Builder hasBlockStateProperties(Block p_81770_) {
        return new LootItemTagCondition.Builder(p_81770_);
    }

    public static class Builder implements LootItemCondition.Builder {
        private final Block block;
        private StatePropertiesPredicate properties = StatePropertiesPredicate.ANY;

        public Builder(Block p_81783_) {
            this.block = p_81783_;
        }

        public LootItemTagCondition.Builder setProperties(StatePropertiesPredicate.Builder p_81785_) {
            this.properties = p_81785_.build();
            return this;
        }

        public LootItemCondition build() {
            return new LootItemTagCondition(this.block, this.properties);
        }
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<LootItemTagCondition> {
        public void serialize(JsonObject p_81795_, LootItemTagCondition p_81796_, JsonSerializationContext p_81797_) {
            p_81795_.addProperty("block", Registry.BLOCK.getKey(p_81796_.block).toString());
            p_81795_.add("properties", p_81796_.properties.serializeToJson());
        }

        public LootItemTagCondition deserialize(JsonObject p_81805_, JsonDeserializationContext p_81806_) {
            ResourceLocation resourcelocation = new ResourceLocation(GsonHelper.getAsString(p_81805_, "block"));
            Block block = Registry.BLOCK.getOptional(resourcelocation).orElseThrow(() -> {
                return new IllegalArgumentException("Can't find block " + resourcelocation);
            });
            StatePropertiesPredicate statepropertiespredicate = StatePropertiesPredicate.fromJson(p_81805_.get("properties"));
            statepropertiespredicate.checkState(block.getStateDefinition(), (p_81790_) -> {
                throw new JsonSyntaxException("Block " + block + " has no property " + p_81790_);
            });
            return new LootItemTagCondition(block, statepropertiespredicate);
        }
    }
}
