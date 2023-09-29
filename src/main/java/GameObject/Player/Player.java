package GameObject.Player;
import GameObject.Player.Inventory.Inventory;
public class Player {
    private String name = "";
    private Inventory inventory = new Inventory();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
