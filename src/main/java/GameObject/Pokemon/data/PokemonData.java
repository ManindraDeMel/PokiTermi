package GameObject.Pokemon.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/**
 * Represents the detailed data attributes of a Pokemon.
 * Includes properties such as ID, name, type, stats, and abilities.
 *
 * @author Manindra de Mel
 */
public class PokemonData {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private List<String> type;

    @JsonProperty("stats")
    private Stats stats;

    @JsonProperty("abilities")
    private List<String> abilities;

    // Default constructor
    public PokemonData() {}

    /**
     * Parametrized constructor
     *
     * @param id ID of the Pokemon.
     * @param name Name of the Pokemon.
     * @param type List of types of the Pokemon.
     * @param stats Stats associated with the Pokemon.
     * @param abilities List of abilities of the Pokemon.
     */
    public PokemonData(int id, String name, List<String> type, Stats stats, List<String> abilities) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stats = stats;
        this.abilities = abilities;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getType() { return type; }
    public void setType(List<String> type) { this.type = type; }
    public Stats getStats() { return stats; }
    public void setStats(Stats stats) { this.stats = stats; }

    public List<String> getAbilities() { return abilities; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }
}
