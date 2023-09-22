package GameObject.Item.Potion;

import GameObject.Item.Item;

public class Potion extends Item {
    private int healAmount;

    public Potion() {
        super();
        super.setName("Potion");
        this.healAmount = 20; // Heals 20 HP
    }

    @Override
    public void use() {
        // Logic to heal a Pokémon goes here
    }

    @Override
    public void display() {
        // Logic to display the item's details goes here
    }
}