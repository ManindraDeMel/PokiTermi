package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

public class Water extends Coordinate {
    public Water(int row, int col) {
        super(row, col);
        setSymbol('~');
        setColor(TextColor.ANSI.CYAN);
    }
}
