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

/**
 * Represents an enemy entity on the map.
 * This class extends the Coordinate class and provides functionalities specific to enemies.
 */
public class Enemy extends Coordinate {
    Pokemon thisPokemon; // The pokemon this enemy is
    /**
     * Constructor to create an instance of Enemy at a specific row and column.
     *
     * @param row The row position of the enemy on the map.
     * @param col The column position of the enemy on the map.
     */
    public Enemy(int row, int col) {
        super(row, col);
        setSymbol('@');  // Set the display symbol for the enemy.
        setColor(TextColor.ANSI.RED);  // Set the display color for the enemy.
        thisPokemon = Pokemon.getRandomPokemons(1).get(0); // init the pokemon
    }

    /**
     * @return this pokemon
     * @author Manindra de Mel
     */
    public Pokemon getThisPokemon() {
        return thisPokemon;
    }

    /**
     * Overrides the display method from the Coordinate class.
     * This method is responsible for displaying the enemy on the map.
     */

    @Override
    public void display() {
        super.display();
    }

    // The following method is commented out but provides a way to generate and return a random enemy Pokemon.
    /*
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
    */
}
