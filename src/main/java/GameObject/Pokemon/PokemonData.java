package GameObject.Pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

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

    // Parametrized constructor
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

class Stats {
    @JsonProperty("atk")
    private int attack;

    @JsonProperty("def")
    private int defense;

    @JsonProperty("health")
    private int health;

    @JsonProperty("type_effectiveness")
    private TypeEffectiveness typeEffectiveness;
    // Default constructor
    public Stats() {}

    // Parametrized constructor
    public Stats(int attack, int defense, int health) {
        this.attack = attack;
        this.defense = defense;
        this.health = health;
    }

    // Getters and setters
    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public TypeEffectiveness getTypeEffectiveness() { return typeEffectiveness; }
    public void setTypeEffectiveness(TypeEffectiveness typeEffectiveness) { this.typeEffectiveness = typeEffectiveness; }
}
class TypeEffectiveness {
    @JsonProperty("super_effective")
    private List<String> superEffective;

    @JsonProperty("not_very_effective")
    private List<String> notVeryEffective;

    @JsonProperty("no_effect")
    private List<String> noEffect;

    // Default constructor
    public TypeEffectiveness() {}

    // Parametrized constructor
    public TypeEffectiveness(List<String> superEffective, List<String> notVeryEffective, List<String> noEffect) {
        this.superEffective = superEffective;
        this.notVeryEffective = notVeryEffective;
        this.noEffect = noEffect;
    }

    // Getters and setters
    public List<String> getSuperEffective() { return superEffective; }
    public void setSuperEffective(List<String> superEffective) { this.superEffective = superEffective; }

    public List<String> getNotVeryEffective() { return notVeryEffective; }
    public void setNotVeryEffective(List<String> notVeryEffective) { this.notVeryEffective = notVeryEffective; }

    public List<String> getNoEffect() { return noEffect; }
    public void setNoEffect(List<String> noEffect) { this.noEffect = noEffect; }
}