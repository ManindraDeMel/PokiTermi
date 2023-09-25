package GameObject.MapEntity;

import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

public class PlayerMapCursor extends Coordinate {
    //player in map

    public PlayerMapCursor(int row, int col) {

        super(row, col);
        setSymbol('@');
        setColor(TextColor.ANSI.MAGENTA_BRIGHT);
    }

    public void moveTo(int dx, int dy) {
        this.setRow(dx);
        this.setCol(dy);
    }


}
