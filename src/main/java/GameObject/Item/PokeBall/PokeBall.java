/**
 * Subclass of Item class, used to define normal/great pokeballs.
 *
 * @author Yusen Nian
 */
package GameObject.Item.PokeBall;


import GameObject.Item.Item;
import GameObject.Player.Inventory.InventoryItem;

public class PokeBall extends Item implements InventoryItem {
    private int quantity;
    private PokeBallType type;

    public PokeBall(String name, int quantity, PokeBallType type) {
        super();
        super.setName(name);
        this.quantity = quantity;
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PokeBallType getType() {
        return type;
    }

    public void setType(PokeBallType type) {
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
    public int getQuantity() {
        return this.quantity;
    }
}