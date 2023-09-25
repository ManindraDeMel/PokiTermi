package GameObject.Pokemon;

public class Pokemon {
    private int health;
    private int attackValue;
    private int defenseValue;

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
