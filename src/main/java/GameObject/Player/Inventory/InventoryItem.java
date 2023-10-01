package GameObject.Player.Inventory;

/**
 * Represents an item that can be stored in the inventory.
 *
 * @author Manindra de Mel
 */
public interface InventoryItem {
    /**
     * Get the name of the item.
     *
     * @return the name of the item.
     */
    String getName();

    /**
     * Gets the quantity of the item.
     *
     * @return quantity of the item
     */
    Integer getQuantity();
    /**
     * Sets the quantity of the item.
     *
     */
    void setQuantity(int quantity);
}
