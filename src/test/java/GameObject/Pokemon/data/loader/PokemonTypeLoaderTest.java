package GameObject.Pokemon.data.loader;

import static org.junit.jupiter.api.Assertions.*;

import GameObject.Pokemon.Pokemon;
import GameObject.Pokemon.data.PokemonData;
import GameObject.Pokemon.data.Type.Type;
import GameObject.Pokemon.data.Type.TypeEffectiveness;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PokemonTypeLoaderTest {

    private static List<PokemonData> allPokemons;
    private static Map<Type, TypeEffectiveness> typeEffectivenessMap;

    @BeforeAll
    public static void setUp() throws IOException {
        // Load all Pokémon data
        allPokemons = PokemonDataLoader.loadPokemonDataFromFile(Pokemon.POKEMON_DATA_FILE_PATH, Pokemon.TYPE_DATA_FILE_PATH);

        // Load all type effectiveness data
        typeEffectivenessMap = PokemonDataLoader.loadTypeEffectivenessData(Pokemon.TYPE_DATA_FILE_PATH);
    }

    @Test
    public void testPokemonTypesHaveCorrectEffectiveness() {
        for (PokemonData pokemon : allPokemons) {
            TypeEffectiveness expectedEffectiveness = new TypeEffectiveness();

            // Merging the expected effectiveness for each type of the Pokémon.
            for (Type type : pokemon.getType()) {
                TypeEffectiveness typeEffectiveness = typeEffectivenessMap.get(type);
                if (typeEffectiveness != null) {
                    expectedEffectiveness.mergeWith(typeEffectiveness);
                }
            }

            TypeEffectiveness actualEffectiveness = pokemon.getStats().getTypeEffectiveness();

            // Printing the expected and actual effectiveness for debugging purposes
            System.out.println("Pokemon: " + pokemon.getName());
            System.out.println("Expected: " + expectedEffectiveness);
            System.out.println("Actual: " + actualEffectiveness);
            System.out.println("---------------");

            // Check if the actual effectiveness corresponds to the merged expected one.
            for (Type type : pokemon.getType()) {
                assertTrue(isEffectivenessEqual(expectedEffectiveness, actualEffectiveness),
                        generateMismatchMessage(type, pokemon.getName(), expectedEffectiveness, actualEffectiveness));
            }
        }
    }

    private boolean isEffectivenessEqual(TypeEffectiveness expected, TypeEffectiveness actual) {
        return expected.getSuperEffective().equals(actual.getSuperEffective())
                && expected.getNotVeryEffective().equals(actual.getNotVeryEffective())
                && expected.getNoEffect().equals(actual.getNoEffect());
    }

    private String generateMismatchMessage(Type type, String pokemonName, TypeEffectiveness expected, TypeEffectiveness actual) {
        StringBuilder msg = new StringBuilder();
        msg.append("Mismatched effectiveness data for type: ").append(type)
                .append(" of Pokemon: ").append(pokemonName).append(".\n");
        if (!expected.getSuperEffective().equals(actual.getSuperEffective())) {
            msg.append("Expected super_effective: ").append(expected.getSuperEffective())
                    .append(", but was: ").append(actual.getSuperEffective()).append(".\n");
        }
        if (!expected.getNotVeryEffective().equals(actual.getNotVeryEffective())) {
            msg.append("Expected not_very_effective: ").append(expected.getNotVeryEffective())
                    .append(", but was: ").append(actual.getNotVeryEffective()).append(".\n");
        }
        if (!expected.getNoEffect().equals(actual.getNoEffect())) {
            msg.append("Expected no_effect: ").append(expected.getNoEffect())
                    .append(", but was: ").append(actual.getNoEffect()).append(".\n");
        }
        return msg.toString();
    }
}
