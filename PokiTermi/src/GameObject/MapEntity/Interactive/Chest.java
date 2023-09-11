package GameObject.MapEntity.Interactive;

import GameObject.MapEntity.Coordinate.Coordinate;

public class Chest extends Coordinate {

    public Chest(int row, int col) {
        super(row, col);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("\u001B[33m" + "C" + "\u001B[0m");
    }
}
