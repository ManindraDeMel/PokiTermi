package GameObject.MapEntity.Interactive;

import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Coordinate.CoordinateInterface;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;

public class Enemy extends Coordinate {
    private static ArrayList<String> enemyList;//load all kinds of enemy monsters at game start
    public static void initEnemyList(){

    }
    public Enemy(int row, int col) {
        super(row, col);
        setSymbol('@');
        setColor(TextColor.ANSI.RED);
    }



    @Override
    public void display() {
        super.display();

    }







}
