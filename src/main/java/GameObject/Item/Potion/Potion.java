package GameObject.Item.Potion;

import GameObject.Item.Item;

public class Potion extends Item {
    public int healAmount;
    public int num;

    public Potion(int num) {
        super();
        super.setName("Potion");
        this.healAmount = 50; // Heals 50 HP
        this.num = num;
    }
}