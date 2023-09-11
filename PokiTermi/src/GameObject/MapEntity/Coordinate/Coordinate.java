package GameObject.MapEntity.Coordinate;

public class Coordinate implements CoordinateInterface{
    int row;
    int col;

    public boolean isSame(Coordinate coordinate) {
        return this.row == coordinate.getRow() && this.col == coordinate.getCol();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
