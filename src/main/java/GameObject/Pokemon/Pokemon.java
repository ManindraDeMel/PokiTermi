package GameObject.Pokemon;

import GameObject.Pokemon.data.PokemonDataLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private PokemonData data;

    public static final String POKEMON_DATA_FILE_PATH = "src/main/java/GameObject/Pokemon/data/pokemon.json";

    public Pokemon(PokemonData data) {
        this.data = data;
    }

    public int getHealth() {
        return data.getStats().getHealth();
    }

    public void setHealth(int health) {
        data.getStats().setHealth(health);
    }

    public int getAttackValue() {
        return data.getStats().getAttack();
    }

    public int getDefenseValue() {
        return data.getStats().getDefense();
    }

    public String getName() {
        return data.getName();
    }

    public List<String> getType() {
        return data.getType();
    }

    public List<String> getAbilities() {
        return data.getAbilities();
    }

    public TypeEffectiveness getTypeEffectiveness() {
        return data.getStats().getTypeEffectiveness();
    }

    public static List<Pokemon> getRandomPokemons(int count) {
        List<Pokemon> randomPokemons = new ArrayList<>();

        try {
            for (int i = 0; i < count; i++) {
                PokemonData randomData = PokemonDataLoader.getRandomPokemonData(POKEMON_DATA_FILE_PATH);
                Pokemon pokemon = new Pokemon(randomData);
                randomPokemons.add(pokemon);
            }
        } catch (IOException e) {
            System.err.println("Error loading Pokemon data: " + e.getMessage());
        }
        return randomPokemons;
    }
    @Override
    public String toString() {
        return getName();
    }
}
