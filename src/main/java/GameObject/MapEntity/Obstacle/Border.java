package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

/**
 * Represents a Border in the game map layout. The Border is an obstacle which has
 * specific symbols based on its position to resemble the appearance of a box.
 *
 * @author Yiming Lu
 */
public class Border extends Coordinate {

    /**
     * Constructs a new Border with the specified row and column positions.
     * Sets color and the appropriate symbol for the border based on its position.
     *
     * @param row the row position of the Border.
     * @param col the column position of the Border.
     */
    public Border(int row, int col) {
        super(row, col);
        setColor(TextColor.ANSI.WHITE);

        // Set the symbol based on the position
        if (this.getCol() == 0 && this.getRow() == 0) {
            setSymbol('\u250C'); // ┌ Left top corner
        } else if (this.getCol() == 0 && this.getRow() == tableRows - 1) {
            setSymbol('\u2514'); // └ Left bottom corner
        } else if (this.getCol() == tableColumns - 1 && this.getRow() == 0) {
            setSymbol('\u2510');  // ┐ Right top corner
        } else if (this.getCol() == tableColumns - 1 && this.getRow() == tableRows - 1) {
            setSymbol('\u2518');  // ┘ Right bottom corner
        } else if (this.getRow() == 0 || this.getRow() == tableRows - 1) {
            setSymbol('\u2550');  // Horizontal line
        } else if (this.getCol() == 0 || this.getCol() == tableColumns - 1) {
            setSymbol('\u2551');  // Vertical line
        }
    }
}
