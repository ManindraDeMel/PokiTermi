package GameObject.Pokemon.data;

import GameObject.Pokemon.PokemonData;

import java.util.List;

/**
 * @author Manindra de Mel
 */
public class PokedexWrapper {
    private List<PokemonData> pokedex;

    public List<PokemonData> getPokedex() {
        return pokedex;
    }

    public void setPokedex(List<PokemonData> pokedex) {
        this.pokedex = pokedex;
    }
}
