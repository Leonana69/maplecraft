package net.maplecraft.items;

import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

import java.util.List;

import static net.maplecraft.utils.PotentialType.getDefaultPotential;

public class SwordStonetoothSwordItem extends SwordWeaponItem {
    public EquipWiseData equipWiseData = new EquipWiseData();
    public SwordStonetoothSwordItem() {
        super(new Item.Properties().durability(100),
                new BonusStats(List.of("ATTACK"), List.of(4)));
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
