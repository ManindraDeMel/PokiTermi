package GameObject.Player.Inventory;

import java.util.HashMap;
import java.util.Map;
import GameObject.Pokemon.Pokemon;

/**
 * Represents the player's inventory.
 *
 * @author Manindra de Mel
 */
public class Inventory {
    private Map<Pokemon, Integer> pokemons;
    private Map<InventoryItem, Integer> items;

    /**
     * Constructor initializes the inventory.
     */
    public Inventory() {
        pokemons = new HashMap<>();
        items = new HashMap<>();
    }

    /**
     * Add a Pokémon to the inventory.
     *
     * @param pokemon the Pokémon object.
     * @param quantity the quantity of the Pokémon.
     */
    public void addPokemon(Pokemon pokemon, int quantity) {
        int currentQuantity = pokemons.getOrDefault(pokemon, 0);
        pokemons.put(pokemon, currentQuantity + quantity);
    }

    /**
     * Add an inventory item (could be Potion, PokeBall, BattleItem).
     *
     * @param item the InventoryItem object.
     */
    public void addInventoryItem(InventoryItem item) {
        int currentQuantity = items.getOrDefault(item, 0);
        items.put(item, currentQuantity + item.getQuantity());
    }

    /**
     * Remove a specific quantity of Pokémon from the inventory.
     *
     * @param pokemon the Pokémon object.
     * @param quantity the quantity to remove.
     */
    public void removePokemon(Pokemon pokemon, int quantity) {
        if (pokemons.containsKey(pokemon)) {
            int currentQuantity = pokemons.get(pokemon);
            int newQuantity = Math.max(0, currentQuantity - quantity);
            if (newQuantity == 0) {
                pokemons.remove(pokemon);
            } else {
                pokemons.put(pokemon, newQuantity);
            }
        }
    }

    /**
     * Use an inventory item, decrementing its quantity.
     *
     * @param item the InventoryItem to use.
     */
    public void useInventoryItem(InventoryItem item) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            int newQuantity = Math.max(0, currentQuantity - 1);
            if (newQuantity == 0) {
                items.remove(item);
            } else {
                items.put(item, newQuantity);
            }
        }
    }

    /**
     * Provides a string representation of the entire inventory.
     *
     * @return string representation of inventory.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Pokemons: \n");
        for (Map.Entry<Pokemon, Integer> entry : pokemons.entrySet()) {
            sb.append(entry.getKey().getClass().getSimpleName()).append(": ").append(entry.getValue()).append("\n");
        }

        sb.append("\nItems: \n");
        for (Map.Entry<InventoryItem, Integer> entry : items.entrySet()) {
            sb.append(entry.getKey().getName()).append(": ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
