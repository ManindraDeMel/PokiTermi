package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

public class Border extends Coordinate {
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
