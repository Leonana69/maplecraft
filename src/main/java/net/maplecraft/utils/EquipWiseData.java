package net.maplecraft.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class EquipWiseData {
    public int starForce = 0;
    public PotentialRarity rarity = PotentialRarity.COMMON;
    public PotentialType[] potentials = PotentialType.getDefaultPotential();
    public String tooltip = null;

    public Tag writeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("star_force", starForce);
        nbt.putInt("potential_rarity", rarity.type);
        nbt.putInt("potentials_0", potentials[0].type);
        nbt.putInt("potentials_1", potentials[1].type);
        nbt.putInt("potentials_2", potentials[2].type);
        return nbt;
    }

    public void readNBT(Tag Tag) {
        CompoundTag nbt = (CompoundTag) Tag;
        starForce = nbt.getInt("star_force");
        rarity = PotentialRarity.get(nbt.getInt("potential_rarity"));
        potentials[0] = PotentialType.VALUES.get(nbt.getInt("potentials_0"));
        potentials[1] = PotentialType.VALUES.get(nbt.getInt("potentials_1"));
        potentials[2] = PotentialType.VALUES.get(nbt.getInt("potentials_2"));
    }

    static String toString(List<Component> list) {
        String fullToolTip = "";
        for (Component component : list) {
            fullToolTip += component.getString() + "&";
        }
        return fullToolTip.substring(0, fullToolTip.length() - 1);
    }

    static List<Component> fromString(String fullToolTip) {
        String [] s = fullToolTip.split("&");
        List<Component> list = new ArrayList<Component>();
        for (String s1 : s) {
            list.add(Component.literal(s1));
        }
        return list;
    }
}
