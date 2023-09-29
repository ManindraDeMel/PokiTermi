package GameObject.Pokemon.data;

import GameObject.Pokemon.PokemonData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
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
    public static List<PokemonData> loadPokemonDataFromFile(String resourcePath) throws IOException {
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
     * Retrieves a random PokemonData object from a file at the given path.
     *
     * @param filePath The path to the file containing the list of Pokemon data.
     * @return A random PokemonData object from the loaded list.
     * @throws IOException if there are issues reading the file or parsing the data.
     */
    public static PokemonData getRandomPokemonData(String filePath) throws IOException {
        List<PokemonData> allPokemons = loadPokemonDataFromFile(filePath);
        return allPokemons.get(RANDOM.nextInt(allPokemons.size()));
    }
}
