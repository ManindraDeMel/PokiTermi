/**
 * Subclass of Item class, used to define normal/great pokeballs.
 *
 * @author Yusen Nian
 */
package GameObject.Item.PokeBall;


import GameObject.Item.Item;

public class PokeBall extends Item {
    private int quantity;
    private PokeBallType type;

    public PokeBall(String name, int quantity, PokeBallType type) {
        super();
        super.setName(name);
        this.quantity = quantity;
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
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
}