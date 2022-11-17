package net.maplecraft.items;

import net.maplecraft.utils.*;

import java.util.List;

import static net.maplecraft.utils.PotentialType.getDefaultPotential;

public class ClawBronzeIgorItem extends ClawWeaponItem {
    public EquipWiseData equipWiseData = new EquipWiseData();
    public ClawBronzeIgorItem() {
        super(new Properties().durability(150),
                new BonusStats(List.of("ATTACK"), List.of(2)));
    }

    @Override
    public void setPotential(PotentialRarity rarity, PotentialType[] potentials) {
        System.out.println("Maple Bow eq: " + equipWiseData.tooltip);
        equipWiseData.rarity = rarity;
        equipWiseData.potentials = potentials;
    }

    @Override
    public void setStarForce(int starForce) {
        equipWiseData.starForce = starForce;
    }

    @Override
    public EquipWiseData getEquipWiseData() {
        return equipWiseData;
    }
}