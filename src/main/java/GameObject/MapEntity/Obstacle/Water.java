package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

/**
 * Represents a Water tile in the game map layout. The Water is an obstacle and
 * is represented by the symbol '~' on the game map.
 *
 * @author Yiming Lu
 */
public class Water extends Coordinate {

    /**
     * Constructs a new Water tile with the specified row and column positions.
     * Sets the symbol and color for the Water tile.
     *
     * @param row the row position of the Water tile.
     * @param col the column position of the Water tile.
     */
    public Water(int row, int col) {
        super(row, col);
        setSymbol('~');
        setColor(TextColor.ANSI.CYAN);
    }
}
