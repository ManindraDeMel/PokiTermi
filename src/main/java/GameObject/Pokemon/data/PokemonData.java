package GameObject.Pokemon.data;

import GameObject.Pokemon.data.Type.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents the detailed data attributes of a Pokemon.
 * Includes properties such as ID, name, type, stats, and abilities.
 * This class is designed to map the JSON properties of a Pokemon's data to Java object attributes.
 *
 * @author Manindra de Mel
 */
public class PokemonData {

    // ID of the Pokemon
    @JsonProperty("id")
    private int id;

    // Name of the Pokemon
    @JsonProperty("name")
    private String name;

    // List of types associated with the Pokemon
    @JsonProperty("type")
    private List<Type> type;

    // Stats of the Pokemon
    @JsonProperty("stats")
    private Stats stats;

    // List of abilities of the Pokemon
    @JsonProperty("abilities")
    private List<String> abilities;

    /**
     * Default constructor.
     */
    public PokemonData() {}

    /**
     * Parametrized constructor to initialize all attributes of the PokemonData.
     *
     * @param id ID of the Pokemon.
     * @param name Name of the Pokemon.
     * @param type List of types of the Pokemon.
     * @param stats Stats associated with the Pokemon.
     * @param abilities List of abilities of the Pokemon.
     */
    public PokemonData(int id, String name, List<Type> type, Stats stats, List<String> abilities) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stats = stats;
        this.abilities = abilities;
    }

    // Getters and setters for the attributes

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Type> getType() { return type; }
    public void setType(List<Type> type) { this.type = type; }

    public Stats getStats() { return stats; }
    public void setStats(Stats stats) { this.stats = stats; }

    public List<String> getAbilities() { return abilities; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }
}
