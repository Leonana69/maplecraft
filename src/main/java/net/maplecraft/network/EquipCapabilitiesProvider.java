package net.maplecraft.network;

import net.maplecraft.utils.EquipWiseData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class EquipCapabilitiesProvider implements ICapabilitySerializable<Tag> {
    public static final Capability<EquipWiseData> EQUIP_CAPABILITIES = CapabilityManager.get(new CapabilityToken<EquipWiseData>() {});
    private final EquipWiseData equipWiseData = new EquipWiseData();
    private final LazyOptional<EquipWiseData> instance = LazyOptional.of(() -> equipWiseData);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == EQUIP_CAPABILITIES ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public Tag serializeNBT() {
        return equipWiseData.writeNBT();
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        equipWiseData.readNBT(nbt);
    }
}
