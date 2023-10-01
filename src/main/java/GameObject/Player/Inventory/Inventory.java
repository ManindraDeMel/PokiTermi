package GameObject.Player.Inventory;

import java.util.ArrayList;
import java.util.List;

import GameObject.Item.BattleItem.BattleItem;
import GameObject.Item.Potion.Potion;
import GameObject.Pokemon.Pokemon;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
/**
 * Represents the player's inventory.
 *
 * @author Manindra de Mel
 */
public class Inventory {
    private List<Pokemon> pokemons;
    private ArrayList<InventoryItem> items;

    /**
     * Constructor initializes the inventory.
     */
    public Inventory() {
        pokemons = new ArrayList<>();
        items = new ArrayList<>();
        initializeWithRandomPokemons(3); // add three random pokemon
        addInventoryItem(new PokeBall("initial pokeballs", 1, PokeBallType.NORMALBALL)); // give player 1 initial pokeball
    }
    /**
     * Initializes the inventory with a set number of random Pokémon.
     *
     * @param count Number of random Pokémon to add to the inventory.
     */
    public void initializeWithRandomPokemons(int count) {
        List<Pokemon> randomPokemons = Pokemon.getRandomPokemons(count);
        for (Pokemon pokemon : randomPokemons) {
            addPokemon(pokemon);
        }
    }

    /**
     * Add a Pokémon to the inventory.
     *
     * @param pokemon the Pokémon object.
     */
    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }
    /**
     * Gets the player's pokemons
     */
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * Add an inventory item (could be Potion, PokeBall, BattleItem).
     *
     * @param item the InventoryItem object.
     */
    public void addInventoryItem(InventoryItem item) {
        for (InventoryItem entry : items) {
            if (entry.equals(item)) {
                entry.setQuantity(entry.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }


    /**
     * Remove a Pokémon by index.
     *
     * @param index the index of the Pokémon to remove.
     */
    public void removePokemon(int index) {
        if (index >= 0 && index < pokemons.size()) {
            pokemons.remove(index);
        }
    }

    /**
     * Use an inventory item, decrementing its quantity. Returns false if the item is not available.
     *
     * @param item the InventoryItem to use.
     * @return true if the item was used successfully, false otherwise.
     */
    public boolean useInventoryItem(InventoryItem item) {
        for (InventoryItem i : items) {
            if (i.equals(item) && i.getQuantity() > 0) {
                i.setQuantity(i.getQuantity() - 1);
                if (i.getQuantity() == 0) {
                    items.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Get the quantity of a specific inventory item.
     *
     * @param item the InventoryItem to check.
     * @return the quantity of the item, or 0 if the item is not in the inventory.
     */
    public int getQuantity(InventoryItem item) {
        for (InventoryItem i : items) {
            if (i.equals(item)) {
                return i.getQuantity();
            }
        }
        return 0;
    }
    public List<InventoryItem> getBattleItems() {
        List<InventoryItem> battleItems = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item instanceof BattleItem) {  // Checking if the item is an instance of BattleItem
                battleItems.add(item);
            }
        }
        return battleItems;
    }
    /**
     * Get the list of Potions in the inventory.
     *
     * @return List of Potions.
     */
    public List<InventoryItem> getPotions() {
        List<InventoryItem> potions = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item instanceof Potion) {  // Checking if the item is an instance of Potion
                potions.add(item);
            }
        }
        return potions;
    }
    public int getIndexOfPokemon(Pokemon pokemon) {
        for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).equals(pokemon)) {
                return i;
            }
        }
        return -1;  // if the Pokémon was not found in the inventory
    }

    /**
     * Completely remove an inventory item from the list, regardless of its quantity.
     *
     * @param item the InventoryItem to be removed.
     * @return true if the item was successfully removed, false otherwise.
     */
    public boolean removeItem(InventoryItem item) {
        return items.remove(item);
    }
    /**
     * Get the list of PokeBalls in the inventory.
     *
     * @return List of PokeBalls.
     */
    public List<InventoryItem> getPokeBalls() {
        List<InventoryItem> pokeBalls = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item instanceof PokeBall) {  // Checking if the item is an instance of PokeBall
                pokeBalls.add(item);
            }
        }
        return pokeBalls;
    }
    public boolean removePokeBall(PokeBall ball) {
        List<InventoryItem> pokeBalls = getPokeBalls();
        for (InventoryItem item : pokeBalls) {
            PokeBall currentBall = (PokeBall) item;
            if (currentBall.getType() == ball.getType()) {
                if (currentBall.getQuantity() >= ball.getQuantity()) {
                    currentBall.setQuantity(currentBall.getQuantity() - ball.getQuantity());
                    if (currentBall.getQuantity() == 0) {
                        items.remove(currentBall);
                    }
                    return true; // Successfully removed the ball(s)
                }
            }
        }
        return false; // Failed to remove the ball(s)
    }
    /**
     * Returns the default Pokémon, which is the first Pokémon in the list.
     *
     * @return the default Pokémon.
     */
    public Pokemon getDefaultPokemon() {
        if (!pokemons.isEmpty()) {
            return pokemons.get(0);
        }
        return null;  // Return null if the player doesn't have any Pokémon.
    }
    /**
     * Returns the default ball, which is of type 'NORMALBALL' or 'GREATBALL'.
     *
     * @return the default ball of type 'NORMALBALL' or 'GREATBALL' or null if not found.
     */
    public PokeBall getDefaultBall() {
        for (InventoryItem item : items) {
            if (item instanceof PokeBall) {
                PokeBall pokeball = (PokeBall) item;
                if (pokeball.getType() == PokeBallType.NORMALBALL) {
                    return pokeball;
                }
            }
        }
        for (InventoryItem item : items) { // if no normal balls, check greatballs
            if (item instanceof PokeBall) {
                PokeBall pokeball = (PokeBall) item;
                if (pokeball.getType() == PokeBallType.GREATBALL) {
                    return pokeball;
                }
            }
        }
        return null; // if player has no balls (lmao)
    }
    /**
     * Provides a string representation of the entire inventory.
     *
     * @return string representation of inventory.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Display Pokemons
        sb.append("Pokemons: \n");
        for (int i = 0; i < pokemons.size(); i++) {
            sb.append(i + 1).append(". ").append(pokemons.get(i).toString()).append("\n"); // Call Pokemon's toString
        }

        // Display Items
        sb.append("\nItems: \n");
        for (InventoryItem item : items) {
            sb.append(item.toString()) // Call InventoryItem's toString
                    .append(": ").append(item.getQuantity()).append("\n");
        }

        return sb.toString();
    }
}
