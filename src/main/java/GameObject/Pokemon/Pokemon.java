/**
 * Used for creat a pokemon.
 *
 * @author Yusen Nian
 */
package GameObject.Pokemon;

public class Pokemon {
    private int health;
    private int attackValue;
    private int defenseValue;
    public static final int MIN_HEALTH = 20;
    public static final int MAX_HEALTH = 200;
    public static final int MIN_ATTACK = 20;
    public static final int MAX_ATTACK = 100;
    public static final int MIN_DEFENCE = 20;
    public static final int MAX_DEFENCE = 100;

    public Pokemon(int health, int attackValue, int defenseValue) {
        this.health = health;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
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
}
