package GameObject.Item.BattleItem;

import GameObject.Item.Item;

public class XAttack extends Item {
    private int boostAmount;

    public XAttack() {
        super();
        super.setName("X Attack");
        this.boostAmount = 1; // Boosts Attack by 1 stage
    }

    @Override
    public void use() {
        // Logic to boost a Pokémon's Attack goes here
    }

    @Override
    public void display() {
        // Logic to display the item's details goes here
    }
}