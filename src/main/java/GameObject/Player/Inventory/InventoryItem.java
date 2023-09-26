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
     * Get the quantity of the item.
     *
     * @return the quantity of the item.
     */
    int getQuantity();
}
