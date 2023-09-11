package GameObject.Item.BattleItem;

import GameObject.Item.Item;

public class XDefense extends Item {
    private int boostAmount;

    public XDefense() {
        super();
        super.setName("X Defense");
        this.boostAmount = 1; // Boosts Defense by 1 stage
    }

    @Override
    public void use() {
        // Logic to boost a Pokémon's Defense goes here
    }

    @Override
    public void display() {
        // Logic to display the item's details goes here
    }
}