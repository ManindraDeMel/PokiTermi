package GameObject.MapEntity.Interactive;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

public class NPC extends Coordinate {
    public NPC(int row, int col) {
        super(row, col);
        setSymbol('@');
        setColor(TextColor.ANSI.BLUE_BRIGHT);
    }

    @Override
    public void display() {
        super.display();

    }
}
