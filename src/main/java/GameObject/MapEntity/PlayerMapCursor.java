package GameObject.MapEntity;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

/**
 * Represents a Player's cursor or position on the game map layout.
 * The PlayerMapCursor serves as a visual representation of where
 * the player is currently located on the map. It is represented
 * by the symbol '@' on the game map.
 *
 * @author Yiming Lu
 */
public class PlayerMapCursor extends Coordinate {
    /**
     * Constructs a new PlayerMapCursor with the specified row and column positions.
     * Sets the symbol and color for the PlayerMapCursor.
     *
     * @param row the row position of the PlayerMapCursor.
     * @param col the column position of the PlayerMapCursor.
     */
    public PlayerMapCursor(int row, int col) {
        super(row, col);
        setSymbol('@');
        setColor(TextColor.ANSI.MAGENTA_BRIGHT);
    }

    /**
     * Moves the PlayerMapCursor to the specified row and column positions.
     *
     * @param dx the new row position for the PlayerMapCursor.
     * @param dy the new column position for the PlayerMapCursor.
     */
    public void moveTo(int dx, int dy) {
        this.setRow(dx);
        this.setCol(dy);
    }
}
