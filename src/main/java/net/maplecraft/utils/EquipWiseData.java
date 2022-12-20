package net.maplecraft.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquipWiseData {
    public int starForce = 0;
    public MapleRarity equipRarity = MapleRarity.COMMON;
    public MapleRarity equipRarityNew = MapleRarity.COMMON;
    public PotentialStats[] potentials = PotentialStats.getDefaultPotentialStats();
    public PotentialStats[] potentialsNew = PotentialStats.getDefaultPotentialStats();
    public String tooltip = "";

    public void updatePotential() {
        this.equipRarity = this.equipRarityNew;
        this.potentials = this.potentialsNew;
        this.equipRarityNew = MapleRarity.COMMON;
        this.potentialsNew = PotentialStats.getDefaultPotentialStats();
    }

    public static EquipWiseData initFromCompoundTag(CompoundTag tag) {
        EquipWiseData data = new EquipWiseData();
        data.starForce = tag.getInt("star_force");
        data.equipRarity = MapleRarity.get(tag.getInt("equip_rarity"));
        data.equipRarityNew = MapleRarity.get(tag.getInt("equip_rarity_new"));
        data.potentials[0] = new PotentialStats(
                MapleRarity.get(tag.getInt("p_rarity_0")),
                PotentialType.VALUES.get(tag.getInt("p_type_0")));
        data.potentials[1] = new PotentialStats(
                MapleRarity.get(tag.getInt("p_rarity_1")),
                PotentialType.VALUES.get(tag.getInt("p_type_1")));
        data.potentials[2] = new PotentialStats(
                MapleRarity.get(tag.getInt("p_rarity_2")),
                PotentialType.VALUES.get(tag.getInt("p_type_2")));

        data.potentialsNew[0] = new PotentialStats(
                MapleRarity.get(tag.getInt("p_rarity_new_0")),
                PotentialType.VALUES.get(tag.getInt("p_type_new_0")));
        data.potentialsNew[1] = new PotentialStats(
                MapleRarity.get(tag.getInt("p_rarity_new_1")),
                PotentialType.VALUES.get(tag.getInt("p_type_new_1")));
        data.potentialsNew[2] = new PotentialStats(
                MapleRarity.get(tag.getInt("p_rarity_new_2")),
                PotentialType.VALUES.get(tag.getInt("p_type_new_2")));

        data.tooltip = tag.getString("tooltip");
        return data;
    }

    public static boolean hasEquipWiseData(CompoundTag tag) {
        return tag.getBoolean("init_equip_wise_data");
    }

    public void addToCompoundTag(CompoundTag tag) {
        tag.putBoolean("init_equip_wise_data", true);
        tag.putInt("star_force", starForce);
        tag.putInt("equip_rarity", equipRarity.type);
        tag.putInt("equip_rarity_new", equipRarityNew.type);
        tag.putInt("p_rarity_0", potentials[0].rarity.type);
        tag.putInt("p_rarity_1", potentials[1].rarity.type);
        tag.putInt("p_rarity_2", potentials[2].rarity.type);
        tag.putInt("p_type_0", potentials[0].type.type);
        tag.putInt("p_type_1", potentials[1].type.type);
        tag.putInt("p_type_2", potentials[2].type.type);
        tag.putInt("p_rarity_new_0", potentialsNew[0].rarity.type);
        tag.putInt("p_rarity_new_1", potentialsNew[1].rarity.type);
        tag.putInt("p_rarity_new_2", potentialsNew[2].rarity.type);
        tag.putInt("p_type_new_0", potentialsNew[0].type.type);
        tag.putInt("p_type_new_1", potentialsNew[1].type.type);
        tag.putInt("p_type_new_2", potentialsNew[2].type.type);
        tag.putString("tooltip", tooltip);
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
