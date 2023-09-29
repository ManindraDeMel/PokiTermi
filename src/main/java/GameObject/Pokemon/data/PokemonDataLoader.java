package GameObject.Pokemon.data;

import GameObject.Pokemon.PokemonData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
/**
 * @author Manindra de Mel
 */
public class PokemonDataLoader {
    private static final Random RANDOM = new Random();

    public static List<PokemonData> loadPokemonDataFromFile(String filePath) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
        ObjectMapper objectMapper = new ObjectMapper();
        PokedexWrapper pokedexWrapper = objectMapper.readValue(jsonData, PokedexWrapper.class);
        return pokedexWrapper.getPokedex();
    }

    public static PokemonData getRandomPokemonData(String filePath) throws IOException {
        List<PokemonData> allPokemons = loadPokemonDataFromFile(filePath);
        return allPokemons.get(RANDOM.nextInt(allPokemons.size()));
    }
}
