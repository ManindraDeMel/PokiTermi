package GameObject.Pokemon.data.Type;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

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
    private List<Type> super_effective;

    @JsonProperty("not_very_effective")
    private List<Type> not_very_effective;

    @JsonProperty("no_effect")
    private List<Type> no_effect;

    /**
     * Default constructor.
     */
    public TypeEffectiveness() {
        super_effective = new ArrayList<>();
        not_very_effective = new ArrayList<>();
        no_effect = new ArrayList<>();

    }

    /**
     * Parameterized constructor.
     *
     * @param super_effective   A list of types that the Pokémon type is super effective against.
     * @param not_very_effective A list of types that the Pokémon type is not very effective against.
     * @param no_effect         A list of types that the Pokémon type has no effect on.
     */
    public TypeEffectiveness(List<Type> super_effective, List<Type> not_very_effective, List<Type> no_effect) {
        this.super_effective = super_effective;
        this.not_very_effective = not_very_effective;
        this.no_effect = no_effect;
    }

    /**
     * Retrieves the list of types that the Pokémon type is super effective against.
     *
     * @return A list of super effective types.
     */
    public List<Type> getSuperEffective() {
        return super_effective;
    }

    /**
     * Sets the list of types that the Pokémon type is super effective against.
     *
     * @param super_effective A list of super effective types.
     */
    public void setSuperEffective(List<Type> super_effective) {
        this.super_effective = super_effective;
    }

    /**
     * Retrieves the list of types that the Pokémon type is not very effective against.
     *
     * @return A list of not very effective types.
     */
    public List<Type> getNotVeryEffective() {
        return not_very_effective;
    }

    /**
     * Sets the list of types that the Pokémon type is not very effective against.
     *
     * @param not_very_effective A list of not very effective types.
     */
    public void setNotVeryEffective(List<Type> not_very_effective) {
        this.not_very_effective = not_very_effective;
    }

    /**
     * Retrieves the list of types that the Pokémon type has no effect on.
     *
     * @return A list of types with no effect.
     */
    public List<Type> getNoEffect() {
        return no_effect;
    }

    /**
     * Sets the list of types that the Pokémon type has no effect on.
     *
     * @param no_effect A list of types with no effect.
     */
    public void setNoEffect(List<Type> no_effect) {
        this.no_effect = no_effect;
    }
    public void mergeWith(TypeEffectiveness other) {
        if (other != null) {
            // Merge super_effective lists
            this.super_effective.addAll(other.getSuperEffective());
            // Remove duplicates
            this.super_effective = new ArrayList<>(new HashSet<>(this.super_effective));

            // Merge not_very_effective lists
            this.not_very_effective.addAll(other.getNotVeryEffective());
            // Remove duplicates
            this.not_very_effective = new ArrayList<>(new HashSet<>(this.not_very_effective));

            // Merge no_effect lists
            this.no_effect.addAll(other.getNoEffect());
            // Remove duplicates
            this.no_effect = new ArrayList<>(new HashSet<>(this.no_effect));
        }
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