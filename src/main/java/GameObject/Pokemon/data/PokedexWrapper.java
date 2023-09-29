package GameObject.Pokemon.data;

import GameObject.Pokemon.PokemonData;

import java.util.List;

/**
 * @author Manindra de Mel
 */
public class PokedexWrapper {
    private PokemonData[] pokedex;

    public PokemonData[] getPokedex() {
        return pokedex;
    }

    public void setPokedex(PokemonData[] pokedex) {
        this.pokedex = pokedex;
    }
}
