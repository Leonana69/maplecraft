package net.maplecraft.utils;

import net.minecraft.network.chat.Component;

import java.util.List;

public class EquipWiseData {
    public PotentialRarity rarity = PotentialRarity.RARE;
    public PotentialType[] potentials = PotentialType.getDefaultPotential();
    public int starForce = 0;
    public List<Component> tooltip = null;
}
