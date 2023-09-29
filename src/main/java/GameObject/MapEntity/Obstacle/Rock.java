package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

/**
 * Represents a Rock in the game map layout. The Rock is an obstacle and
 * is represented by the symbol 'O' on the game map.
 *
 * @author Yiming Lu
 */
public class Rock extends Coordinate {

    /**
     * Constructs a new Rock with the specified row and column positions.
     * Sets the symbol and color for the Rock.
     *
     * @param row the row position of the Rock.
     * @param col the column position of the Rock.
     */
    public Rock(int row, int col) {
        super(row, col);
        setSymbol('O');
        setColor(TextColor.ANSI.WHITE);
    }
}
