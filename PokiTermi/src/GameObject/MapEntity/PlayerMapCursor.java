package GameObject.MapEntity;

import GameObject.MapEntity.Coordinate.Coordinate;

public class PlayerMapCursor extends Coordinate {
    //player in map

    public PlayerMapCursor(int row, int col) {

        super(row, col);
        setSymbol('@');
    }

    public void moveTo(int dx, int dy) {
        this.setRow(dx);
        this.setCol(dy);
    }


}
