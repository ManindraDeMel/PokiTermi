package GameObject.MapEntity.Interactive;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

public class Chest extends Coordinate {

    public Chest(int row, int col) {
        super(row, col);
        setSymbol('?');
        setColor(TextColor.ANSI.YELLOW_BRIGHT);
    }

    @Override
    public void display() {
        super.display();

    }
}
