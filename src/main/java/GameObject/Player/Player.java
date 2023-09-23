package GameObject.Player;

import GameObject.Item.Item;
import GameObject.Pokemon.Pokemon;

import java.util.ArrayList;

public class Player {
    private String name = "";

    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private ArrayList<Item> inventory = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void addPokemons(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }
}
