package GameObject.Player.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import GameObject.Pokemon.Pokemon;

/**
 * Represents the player's inventory.
 *
 * @author Manindra de Mel
 */
public class Inventory {
    private List<Pokemon> pokemons;
    private Map<InventoryItem, Integer> items;

    /**
     * Constructor initializes the inventory.
     */
    public Inventory() {
        pokemons = new ArrayList<>();
        items = new HashMap<>();
        initializeWithRandomPokemons(3);
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
        for (Map.Entry<InventoryItem, Integer> entry : items.entrySet()) {
            if (entry.getKey().equals(item)) {
                items.put(entry.getKey(), entry.getValue() + item.getQuantity());
                return;
            }
        }
        items.put(item, item.getQuantity());
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
     * Use an inventory item, decrementing its quantity. Returns false if item not available.
     *
     * @param item the InventoryItem to use.
     * @return true if item used successfully, false otherwise.
     */
    public boolean useInventoryItem(InventoryItem item) {
        if (items.containsKey(item) && items.get(item) > 0) {
            int currentQuantity = items.get(item);
            int newQuantity = Math.max(0, currentQuantity - 1);
            if (newQuantity == 0) {
                items.remove(item);
            } else {
                items.put(item, newQuantity);
            }
            return true;
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
        return items.getOrDefault(item, 0);
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
        for (Map.Entry<InventoryItem, Integer> entry : items.entrySet()) {
            sb.append(entry.getKey().toString()) // Call InventoryItem's toString
                    .append(": ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
