/**
 * Subclass of Item class, used to define potions.
 *
 * @author Yusen Nian
 */
package GameObject.Item.Potion;

import GameObject.Item.Item;
import GameObject.Player.Inventory.InventoryItem;

public class Potion extends Item implements InventoryItem {
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @author Manindra de Mel
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * @author Manindra de Mel
     */
    @Override
    public int getQuantity() {
        return this.quantity;
    }
    /**
     * @author Manindra de Mel
     */
    @Override
    public String toString() {
        return getName() + " (+" + getHealAmount() + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Potion potion = (Potion) obj;
        return (potion.getName() + getHealAmount() == this.getName() + this.getHealAmount());
    }
}