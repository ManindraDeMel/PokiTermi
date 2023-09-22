package GameObject.Item.BattleItem;


import GameObject.Item.Item;

public class XSpecialDefence extends Item {
    private int boostAmount;

    public XSpecialDefence() {
        super();
        super.setName("X Special Defense");
        this.boostAmount = 1; // Boosts Special Defense by 1 stage
    }

    @Override
    public void use() {
        // Logic to boost a Pokémon's Special Defense goes here
    }

    @Override
    public void display() {
        // Logic to display the item's details goes here
    }
}