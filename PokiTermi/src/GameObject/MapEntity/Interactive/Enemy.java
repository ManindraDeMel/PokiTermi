package GameObject.MapEntity.Interactive;

import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Coordinate.CoordinateInterface;

import java.util.ArrayList;

public class Enemy extends Coordinate {
    private static ArrayList<String> enemyList;//load all kinds of enemy monsters at game start
    public static void initEnemyList(){

    }
    public Enemy(int row, int col) {
        super(row, col);
    }



    @Override
    public void display() {
        super.display();
        System.out.println("\u001B[31m" + "@" + "\u001B[0m");
    }







}
