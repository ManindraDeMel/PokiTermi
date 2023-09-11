package GameObject.MapEntity;

import GameObject.MapEntity.Coordinate.Coordinate;

import java.util.Collections;

public class Cursor extends Coordinate {
    //player in map

    public Cursor(int row, int col) {
        super(row, col);
    }

    public void moveUp() {
        if (getRow() > 0) {
            setRow(getRow() - 1);
        }
    }

    public void moveDown() {
        if (getRow() < tableRows - 1) {
            setRow(getRow() + 1);
        }
    }

    public void moveLeft() {
        if (getCol() > 0) {
            setCol(getCol() - 1);
        }
    }

    public void moveRight() {
        if (getCol() < tableColumns - 1) {
            setCol(getCol() + 1);
        }
    }
    @Override
    public void display() {
        super.display();
        System.out.print("@");
    }
}
