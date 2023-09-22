package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

public class Tree extends Coordinate {
    public Tree(int row, int col) {
        super(row, col);
        setSymbol('F');
        setColor(TextColor.ANSI.GREEN);
    }
}
