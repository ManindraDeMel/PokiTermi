package GameObject.MapEntity.Interactive;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

/**
 * Represents a Door in the game layout which can lead players to different levels.
 *
 * @author Yiming Lu
 */
public class Door extends Coordinate {

    /** The level to which this door leads. */
    private int destinationLevel = 0;

    /**
     * Constructs a new Door with the specified row and column positions and the destination level.
     *
     * @param row the row position of the Door.
     * @param col the column position of the Door.
     * @param destinationLevel the level to which this door leads.
     */
    public Door(int row, int col, int destinationLevel) {
        super(row, col);

        setSymbol('D');
        setColor(TextColor.ANSI.WHITE_BRIGHT);
        this.destinationLevel = destinationLevel;
    }

    /**
     * Retrieves the level to which this door leads.
     *
     * @return the destination level of this door.
     */
    public int getDestinationLevel() {
        return destinationLevel;
    }

    @Override
    public void display() {
        super.display();
    }
}
