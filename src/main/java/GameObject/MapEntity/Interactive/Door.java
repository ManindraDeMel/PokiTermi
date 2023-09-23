package GameObject.MapEntity.Interactive;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

public class Door extends Coordinate {


    private int destinationLevel = 0;

    public Door(int row, int col, int destinationLevel) {
        super(row, col);

        setSymbol('D');
        setColor(TextColor.ANSI.WHITE_BRIGHT);
        this.destinationLevel = destinationLevel;
    }

    public int getDestinationLevel() {
        return destinationLevel;
    }
    @Override
    public void display() {
        super.display();

    }
}