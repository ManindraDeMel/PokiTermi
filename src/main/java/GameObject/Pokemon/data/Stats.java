package GameObject.Pokemon.data;

import GameObject.Pokemon.data.Type.TypeEffectiveness;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the stats attributes of a Pokemon.
 * Includes properties such as attack, defense, and health values.
 *
 * @author Manindra de Mel
 */
public class Stats {
    @JsonProperty("atk")
    private int attack;

    @JsonProperty("def")
    private int defense;

    @JsonProperty("health")
    private int health;

    @JsonProperty("type_effectiveness")
    private TypeEffectiveness typeEffectiveness;

    // Default constructor
    public Stats() {
    }

    /**
     * Parametrized constructor
     *
     * @param attack  Attack value of the Pokemon.
     * @param defense Defense value of the Pokemon.
     * @param health  Health value of the Pokemon.
     */
    public Stats(int attack, int defense, int health) {
        this.attack = attack;
        this.defense = defense;
        this.health = health;
    }

    // Getters and setters
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public TypeEffectiveness getTypeEffectiveness() {
        return typeEffectiveness;
    }

    public void setTypeEffectiveness(TypeEffectiveness typeEffectiveness) {
        this.typeEffectiveness = typeEffectiveness;
    }
}
