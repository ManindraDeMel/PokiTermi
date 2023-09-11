package GameObject.MapEntity.Interactive;

import GameObject.MapEntity.Coordinate.Coordinate;

public class NPC extends Coordinate {
    public NPC(int row, int col) {
        super(row, col);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("\u001B[34m" + "@" + "\u001B[0m");
    }
}
