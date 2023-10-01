/**
 * Subclass of Item class, used to define Attack/Defence items.
 *
 * @author Yusen Nian
 */
package GameObject.Item.BattleItem;

import GameObject.Item.Item;
import GameObject.Player.Inventory.InventoryItem;

public class BattleItem extends Item implements InventoryItem {
    private int boostAmount;
    private int quantity;
    private BattleItemType type;

    /**
     * Places a given entity at a random location within the game map.
     * The method ensures the entity is placed at an empty spot.
     *
     * @param quantity the numbers of item
     * @param type the type of item
     * @author Yusen Nian
     */
    public BattleItem(int quantity, BattleItemType type) {
        super();
        super.setName("BattleItem");
        this.boostAmount = 20; // Boosts Special Attack/Defence by 20.
        this.quantity = quantity;
        this.type = type;
    }

    public BattleItem(int quantity, BattleItemType type, int boostAmount) {
        super();
        super.setName("BattleItem");
        this.boostAmount = boostAmount;
        this.quantity = quantity;
        this.type = type;
    }

    public int getBoostAmount() {
        return boostAmount;
    }

    public void setBoostAmount(int boostAmount) {
        this.boostAmount = boostAmount;
    }
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BattleItemType getType() {
        return type;
    }

    public void setType(BattleItemType type) {
        this.type = type;
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
    public Integer getQuantity() {
        return this.quantity;
    }
    /**
     * @author Manindra de Mel
     */
    @Override
    public String toString() {
        return getType().toString() + " (+" + getBoostAmount() + ")";
    }
    /**
     * @author Manindra de Mel
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BattleItem battleitem = (BattleItem) obj;
        return (battleitem.getType() == this.getType());
    }
}