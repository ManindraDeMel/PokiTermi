package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;

public class Water extends Coordinate {
    public Water(int row, int col) {
        super(row, col);
    }

    @Override
    public void display() {
        super.display();
        System.out.print("\u001B[34m"+"~"+"\u001B[0m");
    }
}
