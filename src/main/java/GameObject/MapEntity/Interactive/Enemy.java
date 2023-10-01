package GameObject.MapEntity.Interactive;

import GameObject.Item.Item;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Coordinate.CoordinateInterface;
import GameObject.Pokemon.Pokemon;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Represents an enemy entity on the map.
 * This class extends the Coordinate class and provides functionalities specific to enemies.
 */
public class Enemy extends Coordinate {
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
    }
    /**
     * Overrides the display method from the Coordinate class.
     * This method is responsible for displaying the enemy on the map.
     */

    @Override
    public void display() {
        super.display();
    }

}
