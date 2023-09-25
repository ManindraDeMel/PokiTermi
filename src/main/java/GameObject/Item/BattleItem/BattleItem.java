/**
 * Subclass of Item class, used to define Attack/Defence items.
 *
 * @author Yusen Nian
 */
package GameObject.Item.BattleItem;

import GameObject.Item.Item;

public class BattleItem extends Item {
    private int boostAmount;
    private int quantity;
    private BattleItemType type;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BattleItemType getType() {
        return type;
    }

    public void setType(BattleItemType type) {
        this.type = type;
    }
}