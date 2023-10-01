package GameObject.Pokemon;

import GameObject.Pokemon.data.PokemonData;
import GameObject.Pokemon.data.Type.Type;
import GameObject.Pokemon.data.loader.PokemonDataLoader;
import GameObject.Pokemon.data.Type.TypeEffectiveness;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Pokemon in the game.
 * This class provides the attributes and behaviors for a Pokemon,
 * including its name, stats, type, abilities, and related functionalities.
 * It also allows fetching random Pokemon entities for gameplay purposes.
 *
 * @author Manindra de Mel
 */
public class Pokemon {

    /** Holds the detailed data of a Pokemon. */
    private PokemonData data;

    /** Path to the data file containing details about various Pokemon. */
    public static final String POKEMON_DATA_FILE_PATH = "/GameObject/Pokemon/data/pokemon.json";
    /** Path to the data file containing details about various Pokemon types and their effectiveness against other types. */
    public static final String TYPE_DATA_FILE_PATH = "/GameObject/Pokemon/data/type_effectiveness.json";
    public Pokemon() {data = new PokemonData();}; // Default constructor
    /**
     * Constructor that initializes the Pokemon with the given data.
     *
     * @param data the detailed data of a Pokemon.
     */
    public Pokemon(PokemonData data) {
        this.data = data;
    }

    /**
     * Gets the health value of the Pokemon.
     *
     * @return health value of the Pokemon.
     */
    public int getHealth() {
        return data.getStats().getHealth();
    }

    /**
     * Sets the health value of the Pokemon.
     *
     * @param health the health value to be set.
     */
    public void setHealth(int health) {
        data.getStats().setHealth(health);
    }

    /**
     * Retrieves the attack value of the Pokemon.
     *
     * @return attack value of the Pokemon.
     */
    public int getAttackValue() {
        return data.getStats().getAttack();
    }

    /**
     * Retrieves the defense value of the Pokemon.
     *
     * @return defense value of the Pokemon.
     */
    public int getDefenseValue() {
        return data.getStats().getDefense();
    }

    /**
     * Retrieves the name of the Pokemon.
     *
     * @return name of the Pokemon.
     */
    public String getName() {
        return data.getName();
    }

    /**
     * Retrieves the types of the Pokemon.
     *
     * @return list of types of the Pokemon.
     */
    public List<Type> getType() {
        return data.getType();
    }

    /**
     * Retrieves the abilities of the Pokemon.
     *
     * @return list of abilities of the Pokemon.
     */
    public List<String> getAbilities() {
        return data.getAbilities();
    }

    /**
     * Retrieves the type effectiveness of the Pokemon.
     *
     * @return type effectiveness of the Pokemon.
     */
    public TypeEffectiveness getTypeEffectiveness() {
        return data.getStats().getTypeEffectiveness();
    }

    /**
     * Fetches a list of random Pokemon entities.
     *
     * @param count the number of random Pokemon entities to fetch.
     * @return list of random Pokemon entities.
     */
    public static List<Pokemon> getRandomPokemons(int count) {
        List<Pokemon> randomPokemons = new ArrayList<>();

        try {
            for (int i = 0; i < count; i++) {
                PokemonData randomData = PokemonDataLoader.getRandomPokemonData(POKEMON_DATA_FILE_PATH, TYPE_DATA_FILE_PATH);
                Pokemon pokemon = new Pokemon(randomData);
                randomPokemons.add(pokemon);
            }
        } catch (IOException e) {
            System.err.println("Error loading Pokemon data: " + e.getMessage());
        }
        return randomPokemons;
    }

    /**
     * Provides a string representation of the Pokemon, primarily its name.
     *
     * @return the name of the Pokemon.
     */
    @Override
    public String toString() {
        return getName();
    }
}
