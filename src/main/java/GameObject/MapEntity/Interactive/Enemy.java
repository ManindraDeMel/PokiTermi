package GameObject.MapEntity.Interactive;

import GameObject.Item.Item;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Coordinate.CoordinateInterface;
import GameObject.Pokemon.Pokemon;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Coordinate {
    private static ArrayList<String> enemyList;//load all kinds of enemy monsters at game start
    public static void initEnemyList(){

    }
    public Enemy(int row, int col) {
        super(row, col);
        setSymbol('@');
        setColor(TextColor.ANSI.RED);
    }



    @Override
    public void display() {
        super.display();

    }

    /**
     * Public method to generate and return an enemy Pokemon.
     *
     * @authot Yusen Nian
     * @return A randomly generated enemy Pokemon.
     */
    public Pokemon open(){

        Random random = new Random();
        int health = random.nextInt(Pokemon.MAX_HEALTH - Pokemon.MIN_HEALTH + 1) + Pokemon.MIN_HEALTH;
        int attack = random.nextInt(Pokemon.MAX_ATTACK - Pokemon.MIN_ATTACK + 1) + Pokemon.MIN_ATTACK;
        int defence = random.nextInt(Pokemon.MAX_DEFENCE - Pokemon.MIN_DEFENCE + 1) + Pokemon.MIN_DEFENCE;
        // Create an instance of enemy pokemon.
        Pokemon pokemon = new Pokemon(health, attack, defence);


        // Add information at the same position with lookAround text
        String message = "You  found an enemy!";

        return pokemon;
    }


}
