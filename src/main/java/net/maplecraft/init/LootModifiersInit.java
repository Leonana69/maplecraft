package net.maplecraft.init;

import com.mojang.serialization.Codec;
import net.maplecraft.MapleCraftMod;
import net.maplecraft.loot.AddItemModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootModifiersInit {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MapleCraftMod.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_MAPLE_ITEM =
            REGISTRY.register("add_maple_item", AddItemModifier.CODEC);
}
