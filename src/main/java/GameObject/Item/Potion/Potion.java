/**
 * Subclass of Item class, used to define potions.
 *
 * @author Yusen Nian
 */
package GameObject.Item.Potion;

import GameObject.Item.Item;

public class Potion extends Item {
    private int healAmount;
    private int quantity;

    public Potion(int quantity) {
        super();
        super.setName("Potion");
        this.healAmount = 50; // Heals 50 HP
        this.quantity = quantity;
    }

    public Potion(int quantity, int healAmount) {
        super();
        super.setName("Potion");
        this.healAmount = healAmount;
        this.quantity = quantity;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}