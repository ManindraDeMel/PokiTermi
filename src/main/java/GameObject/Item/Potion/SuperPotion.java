package GameObject.Item.Potion;


import GameObject.Item.Item;

public class SuperPotion extends Item {
    private int healAmount;

    public SuperPotion() {
        super();
        super.setName("Super Potion");
        this.healAmount = 50; // Heals 50 HP
    }

    @Override
    public void use() {
        // Logic to heal a Pokémon with a higher amount goes here
    }

    @Override
    public void display() {
        // Logic to display the item's details goes here
    }
}