package GameObject.Pokemon.data;

import GameObject.Pokemon.PokemonData;

/**
 * Represents a wrapper for the Pokedex, which contains an array of PokemonData.
 * This class can be used to group and manage multiple PokemonData objects in an array format.
 *
 * @author Manindra de Mel
 */
public class PokedexWrapper {

    /** An array containing the detailed data attributes of various Pokemons. */
    private PokemonData[] pokedex;

    /**
     * Retrieves the Pokedex array.
     *
     * @return An array of PokemonData objects.
     */
    public PokemonData[] getPokedex() {
        return pokedex;
    }

    /**
     * Sets the Pokedex array.
     *
     * @param pokedex An array of PokemonData objects to be set for the Pokedex.
     */
    public void setPokedex(PokemonData[] pokedex) {
        this.pokedex = pokedex;
    }
}
