// GreatBall class
package GameObject.Item.PokeBall;

import GameObject.Item.Item;

public class GreatBall extends Item {
    private int catchRate;

    public GreatBall() {
        super();
        super.setName("Great Ball");
        this.catchRate = 2; // A better catch rate than Poké Ball
    }

    @Override
    public void use() {
        // Logic to attempt to catch a Pokémon with a higher rate goes here
    }

    @Override
    public void display() {
        // Logic to display the item's details goes here
    }
}