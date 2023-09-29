package GameObject.MapEntity.Coordinate;

import Game.GameLayout;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

/**
 * Represents a Coordinate in the game layout with properties for positioning and visualization.
 *
 * @author Yiming Lu
 */
public class Coordinate implements CoordinateInterface {

    private int row;
    private int col;
    private boolean isAccessible = false;
    private Character symbol = '?';
    private TextColor color;

    public static int tableRows = 20;
    public static int tableColumns = 20;

    /**
     * Constructs a new Coordinate with the specified row and column positions.
     *
     * @param row the row position of the Coordinate.
     * @param col the column position of the Coordinate.
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Checks if the given coordinate matches this Coordinate's position.
     *
     * @param coordinate the coordinate to check against.
     * @return true if the positions match, false otherwise.
     */
    public boolean isSame(Coordinate coordinate) {
        return this.row == coordinate.getRow() && this.col == coordinate.getCol();
    }

    /**
     * Retrieves the row position of the Coordinate.
     *
     * @return the row position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row position of the Coordinate.
     *
     * @param row the row position to set.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Retrieves the column position of the Coordinate.
     *
     * @return the column position.
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the column position of the Coordinate.
     *
     * @param col the column position to set.
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Displays the Coordinate on the terminal using its properties for positioning and visualization.
     */
    public void display() {
        Terminal terminal = GameLayout.getTerminal();
        if (this.color == null) {
            this.color = TextColor.ANSI.DEFAULT;
        }
        try {
            terminal.setCursorPosition(this.getCol(), this.getRow());
            terminal.setForegroundColor(this.color);
            terminal.putCharacter(this.symbol);
            terminal.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the visualization color of the Coordinate.
     *
     * @return the TextColor for this Coordinate.
     */
    public TextColor getColor() {
        return color;
    }

    /**
     * Sets the visualization color for this Coordinate.
     *
     * @param color the TextColor to set.
     */
    public void setColor(TextColor color) {
        this.color = color;
    }

    /**
     * Retrieves the visualization symbol for this Coordinate.
     *
     * @return the Character symbol for this Coordinate.
     */
    public Character getSymbol() {
        return symbol;
    }

    /**
     * Sets the visualization symbol for this Coordinate.
     *
     * @param symbol the Character symbol to set.
     */
    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    /**
     * Checks if this Coordinate is accessible in the game.
     *
     * @return true if accessible, false otherwise.
     */
    public boolean isAccessible() {
        return isAccessible;
    }

    /**
     * Sets the accessibility status for this Coordinate.
     *
     * @param accessible the accessibility status to set.
     */
    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }
}
