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
 * @author Manindra de Mel
 */
public class PokemonDataLoader {
    private static final Random RANDOM = new Random();

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


    public static PokemonData getRandomPokemonData(String filePath) throws IOException {
        List<PokemonData> allPokemons = loadPokemonDataFromFile(filePath);
        return allPokemons.get(RANDOM.nextInt(allPokemons.size()));
    }
}
