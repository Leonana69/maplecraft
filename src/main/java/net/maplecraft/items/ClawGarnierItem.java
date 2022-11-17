package net.maplecraft.items;

import net.maplecraft.utils.*;
import net.minecraft.world.item.*;

import java.util.List;

import static net.maplecraft.utils.PotentialType.getDefaultPotential;

public class ClawGarnierItem extends ClawWeaponItem {
    public EquipWiseData equipWiseData = new EquipWiseData();
    public ClawGarnierItem() {
        super(new Item.Properties().durability(100),
                new BonusStats(List.of("ATTACK"), List.of(1)));
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