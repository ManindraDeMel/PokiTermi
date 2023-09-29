package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

/**
 * Represents a Tree in the game map layout. The Tree is an obstacle and
 * is represented by the symbol 'F' on the game map.
 *
 * @author Yiming Lu
 */
public class Tree extends Coordinate {

    /**
     * Constructs a new Tree with the specified row and column positions.
     * Sets the symbol and color for the Tree.
     *
     * @param row the row position of the Tree.
     * @param col the column position of the Tree.
     */
    public Tree(int row, int col) {
        super(row, col);
        setSymbol('F');
        setColor(TextColor.ANSI.GREEN);
    }
}
