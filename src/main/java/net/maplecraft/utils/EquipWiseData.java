package net.maplecraft.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class EquipWiseData {
    public int starForce = 0;
    public MapleRarity equipRarity = MapleRarity.COMMON;
    public PotentialStats[] potentials = PotentialStats.getDefaultPotentialStats();
    public String tooltip = null;

    public Tag writeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("star_force", starForce);
        nbt.putInt("equip_rarity", equipRarity.type);
        nbt.putInt("potential_rarity_0", potentials[0].rarity.type);
        nbt.putInt("potential_rarity_1", potentials[1].rarity.type);
        nbt.putInt("potential_rarity_2", potentials[2].rarity.type);
        nbt.putInt("potential_type_0", potentials[0].type.type);
        nbt.putInt("potential_type_1", potentials[1].type.type);
        nbt.putInt("potential_type_2", potentials[2].type.type);
        return nbt;
    }

    public void readNBT(Tag Tag) {
        CompoundTag nbt = (CompoundTag) Tag;
        starForce = nbt.getInt("star_force");
        equipRarity = MapleRarity.get(nbt.getInt("equip_rarity"));
        potentials[0] = new PotentialStats(
                MapleRarity.get(nbt.getInt("potential_rarity_0")),
                PotentialType.VALUES.get(nbt.getInt("potential_type_0")));
        potentials[1] = new PotentialStats(
                MapleRarity.get(nbt.getInt("potential_rarity_1")),
                PotentialType.VALUES.get(nbt.getInt("potential_type_1")));
        potentials[2] = new PotentialStats(
                MapleRarity.get(nbt.getInt("potential_rarity_2")),
                PotentialType.VALUES.get(nbt.getInt("potential_type_2")));
    }

    static String componentToString(List<Component> list) {
        String fullToolTip = "";
        for (Component component : list) {
            fullToolTip += component.getString() + "&";
        }
        return fullToolTip.substring(0, fullToolTip.length() - 1);
    }

    static List<Component> componentFromString(String fullToolTip) {
        String [] s = fullToolTip.split("&");
        List<Component> list = new ArrayList<Component>();
        for (String s1 : s) {
            list.add(Component.literal(s1));
        }
        return list;
    }
}
