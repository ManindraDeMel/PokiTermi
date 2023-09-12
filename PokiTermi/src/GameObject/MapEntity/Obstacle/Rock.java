package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

public class Rock extends Coordinate {
    public Rock(int row, int col) {
        super(row, col);
        setSymbol('O');
        setColor(TextColor.ANSI.WHITE);
    }
}
