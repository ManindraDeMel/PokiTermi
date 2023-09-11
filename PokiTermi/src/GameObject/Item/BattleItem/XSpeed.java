package GameObject.Item.BattleItem;

import GameObject.Item.Item;

public class XSpeed extends Item {
    private int boostAmount;

    public XSpeed() {
        super();
        super.setName("X Speed");
        this.boostAmount = 1; // Boosts Speed by 1 stage
    }

    @Override
    public void use() {
        // Logic to boost a Pokémon's Speed goes here
    }

    @Override
    public void display() {
        // Logic to display the item's details goes here
    }
}