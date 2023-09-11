package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;

public class Tree extends Coordinate{

    public Tree(int row, int col) {
        super(row, col);
    }

    @Override
    public void display() {
        super.display();
        System.out.print("\u001B[32m" + "F" + "\u001B[0m");
    }
}
