package GameObject.Item.BattleItem;

import GameObject.Item.Item;

public class XSpecialAttack extends Item {
    private int boostAmount;

    public XSpecialAttack() {
        super();
        super.setName("X Special Attack");
        this.boostAmount = 1; // Boosts Special Attack by 1 stage
    }

    @Override
    public void use() {
        // Logic to boost a Pokémon's Special Attack goes here
    }

    @Override
    public void display() {
        // Logic to display the item's details goes here
    }
}