/**
 * Used for creat a pokemon.
 *
 * @author Yusen Nian
 */
package GameObject.Pokemon;

import java.util.List;

public class Pokemon {
    private int health;
    private int attackValue;
    private int defenseValue;
    private String name;
    private List<String> type;
    private List<String> location;
    private List<String> abilities;
    private TypeEffectiveness typeEffectiveness;

    public Pokemon(PokemonData data) {
        this.health = data.getStats().getHealth();
        this.attackValue = data.getStats().getAttack();
        this.defenseValue = data.getStats().getDefense();
        this.name = data.getName();
        this.type = data.getType();
        this.abilities = data.getAbilities();
        this.typeEffectiveness = data.getStats().getTypeEffectiveness();
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getType() { return type; }
    public void setType(List<String> type) { this.type = type; }
    public List<String> getLocation() { return location; }
    public void setLocation(List<String> location) { this.location = location; }
    public List<String> getAbilities() { return abilities; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }
    public TypeEffectiveness getTypeEffectiveness() { return typeEffectiveness; }
    public void setTypeEffectiveness(TypeEffectiveness typeEffectiveness) { this.typeEffectiveness = typeEffectiveness; }
}
