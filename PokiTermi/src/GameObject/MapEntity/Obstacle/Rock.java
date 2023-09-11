package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;

public class Rock extends Coordinate{

    public Rock(int row, int col) {
        super(row, col);
    }

    @Override
    public void display() {
        super.display();
        System.out.print("O");
    }
}
