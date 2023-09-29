package GameObject.Player;

import GameObject.Player.Inventory.Inventory;

/**
 * Represents the Player in the game.
 * This class provides the attributes and behaviors for a game player,
 * including the player's name and inventory. The inventory contains
 * items that the player collects or uses during the course of the game.
 *
 * @author Yiming Lu
 */
public class Player {

    /** The name of the player. */
    private String name = "";

    /** The player's inventory where items are stored. */
    private Inventory inventory = new Inventory();

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name the name to set for the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the inventory of the player.
     * The inventory contains items that the player collects or uses.
     *
     * @return the player's inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }
}
