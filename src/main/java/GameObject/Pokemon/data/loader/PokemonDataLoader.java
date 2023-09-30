package GameObject.Pokemon.data.loader;
import GameObject.Pokemon.data.PokemonData;
import GameObject.Pokemon.data.Type.Type;
import GameObject.Pokemon.data.Type.TypeDeserializer;
import GameObject.Pokemon.data.Type.TypeEffectiveness;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A utility class to assist in loading Pokemon data from a file.
 * Provides functionalities to read Pokemon data from a given resource path,
 * and to randomly select a Pokemon data from the loaded data.
 *
 * @author Manindra de Mel
 */
public class PokemonDataLoader {

    /** A constant random number generator for generating random indices. */
    private static final Random RANDOM = new Random();

    /**
     * Loads a list of PokemonData objects from a file resource at the provided path.
     * This function utilizes Jackson ObjectMapper to map JSON data to Java objects.
     *
     * @param resourcePath The path to the resource file containing the Pokemon data.
     * @return A list of PokemonData objects.
     * @throws IOException if there are issues reading the file or parsing the data.
     */
    public static List<PokemonData> loadRawPokemonDataFromFile(String resourcePath) throws IOException {
        try (InputStream is = PokemonDataLoader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + resourcePath);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            PokedexWrapper wrapper = objectMapper.readValue(is, PokedexWrapper.class);
            return Arrays.asList(wrapper.getPokedex());
        }
    }

    /**
     * Loads the type effectiveness data from a given JSON file.
     *
     * @param resourcePath The path to the resource file containing the type effectiveness data.
     * @return A map of Pokémon types to their corresponding TypeEffectiveness.
     * @throws IOException if there are issues reading the file or parsing the data.
     */
    public static Map<Type, TypeEffectiveness> loadTypeEffectivenessData(String resourcePath) throws IOException {
        try (InputStream is = PokemonDataLoader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + resourcePath);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(
                    new SimpleModule().addKeyDeserializer(Type.class, new TypeDeserializer())
            );
            return objectMapper.readValue(is, new TypeReference<>() {
            });
        }
    }


    /**
     * Loads a list of PokemonData objects from a file resource at the provided path.
     * This function utilizes Jackson ObjectMapper to map JSON data to Java objects.
     *
     * @param pokemonDataPath The path to the resource file containing the Pokemon data.
     * @param typeEffectivenessPath The path to the resource file containing the Type pokemon data.
     * @return A list of PokemonData objects.
     * @throws IOException if there are issues reading the file or parsing the data.
     */
    public static List<PokemonData> loadPokemonDataFromFile(String pokemonDataPath, String typeEffectivenessPath) throws IOException {
        List<PokemonData> pokemons = loadRawPokemonDataFromFile(pokemonDataPath); // This can be your previous method
        Map<Type, TypeEffectiveness> typeEffectivenessMap = loadTypeEffectivenessData(typeEffectivenessPath);

        for (PokemonData pokemon : pokemons) {
            for (String type : pokemon.getType()) {
                if (typeEffectivenessMap.containsKey(type)) {
                    pokemon.getStats().setTypeEffectiveness(typeEffectivenessMap.get(type));
                    break; // assuming one type effectiveness is set for each Pokémon, otherwise, remove the break
                }
            }
        }

        return pokemons;
    }


    /**
     * Retrieves a random PokemonData object from a file at the given path.
     *
     * @param pokemonDataPath The path to the resource file containing the Pokemon data.
     * @param typeEffectivenessPath The path to the resource file containing the Type pokemon data.
     * @return A random PokemonData object from the loaded list.
     * @throws IOException if there are issues reading the file or parsing the data.
     */
    public static PokemonData getRandomPokemonData(String pokemonDataPath, String typeEffectivenessPath) throws IOException {
        List<PokemonData> allPokemons = loadPokemonDataFromFile(pokemonDataPath, typeEffectivenessPath);
        return allPokemons.get(RANDOM.nextInt(allPokemons.size()));
    }
}
