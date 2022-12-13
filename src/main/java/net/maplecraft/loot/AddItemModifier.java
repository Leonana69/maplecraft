package net.maplecraft.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

import static java.lang.Math.abs;

public class AddItemModifier extends LootModifier {
    public static class DropEntry {
        public Item item;
        public float chance;
        public DropEntry(Item item, float chance) {
            this.item = item;
            this.chance = chance;
        }
    }

    public static Supplier<Codec<DropEntry>> DropEntryCodec = Suppliers.memoize(() ->
            RecordCodecBuilder.create(instance ->
                    instance.group(
                            ForgeRegistries.ITEMS
                                    .getCodec()
                                    .fieldOf("item")
                                    .forGetter(entry -> entry.item),

                            Codec.FLOAT
                                    .fieldOf("chance")
                                    .forGetter(entry -> entry.chance)
                    ).apply(instance, DropEntry::new))
    );

    public static Supplier<Codec<AddItemModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(instance -> codecStart(instance).and(
                    DropEntryCodec.get().listOf()
                                    .fieldOf("drops")
                                    .forGetter(entry -> entry.drops)).apply(instance, AddItemModifier::new))
    );

    private final List<DropEntry> drops;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected AddItemModifier(LootItemCondition[] conditionsIn, List<DropEntry> list) {
        super(conditionsIn);
        this.drops = list;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        int maxLootCount = 4;
        for (DropEntry drop : drops) {
            if (abs(context.getRandom().nextFloat()) <= drop.chance) {
                generatedLoot.add(new ItemStack(drop.item));
                maxLootCount--;
            }

            if (maxLootCount == 0) break;
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
