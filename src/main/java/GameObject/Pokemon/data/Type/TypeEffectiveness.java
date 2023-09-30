package GameObject.Pokemon.data.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the type effectiveness attributes of a Pokemon.
 * Indicates which types a Pokemon is super effective against, not very effective against, or has no effect on.
 *
 * @author Manindra de Mel
 */
/**
 * Represents the effectiveness of a Pokémon type against other types.
 * Contains lists indicating which types a given type is super effective against,
 * not very effective against, or has no effect on.
 *
 * @author Manindra de Mel
 */
public class TypeEffectiveness {

    @JsonProperty("super_effective")
    private List<String> super_effective;

    @JsonProperty("not_very_effective")
    private List<String> not_very_effective;

    @JsonProperty("no_effect")
    private List<String> no_effect;

    /**
     * Default constructor.
     */
    public TypeEffectiveness() {
    }

    /**
     * Parameterized constructor.
     *
     * @param super_effective   A list of types that the Pokémon type is super effective against.
     * @param not_very_effective A list of types that the Pokémon type is not very effective against.
     * @param no_effect         A list of types that the Pokémon type has no effect on.
     */
    public TypeEffectiveness(List<String> super_effective, List<String> not_very_effective, List<String> no_effect) {
        this.super_effective = super_effective;
        this.not_very_effective = not_very_effective;
        this.no_effect = no_effect;
    }

    /**
     * Retrieves the list of types that the Pokémon type is super effective against.
     *
     * @return A list of super effective types.
     */
    public List<String> getSuperEffective() {
        return super_effective;
    }

    /**
     * Sets the list of types that the Pokémon type is super effective against.
     *
     * @param super_effective A list of super effective types.
     */
    public void setSuperEffective(List<String> super_effective) {
        this.super_effective = super_effective;
    }

    /**
     * Retrieves the list of types that the Pokémon type is not very effective against.
     *
     * @return A list of not very effective types.
     */
    public List<String> getNotVeryEffective() {
        return not_very_effective;
    }

    /**
     * Sets the list of types that the Pokémon type is not very effective against.
     *
     * @param not_very_effective A list of not very effective types.
     */
    public void setNotVeryEffective(List<String> not_very_effective) {
        this.not_very_effective = not_very_effective;
    }

    /**
     * Retrieves the list of types that the Pokémon type has no effect on.
     *
     * @return A list of types with no effect.
     */
    public List<String> getNoEffect() {
        return no_effect;
    }

    /**
     * Sets the list of types that the Pokémon type has no effect on.
     *
     * @param no_effect A list of types with no effect.
     */
    public void setNoEffect(List<String> no_effect) {
        this.no_effect = no_effect;
    }

    @Override
    public String toString() {
        return "TypeEffectiveness{" +
                "super_effective=" + super_effective +
                ", not_very_effective=" + not_very_effective +
                ", no_effect=" + no_effect +
                '}';
    }
}