package Game;

import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Interactive.Chest;
import GameObject.MapEntity.Interactive.Door;
import GameObject.MapEntity.Interactive.Enemy;
import GameObject.MapEntity.Interactive.NPC;
import GameObject.MapEntity.Obstacle.Border;
import GameObject.MapEntity.Obstacle.Rock;
import GameObject.MapEntity.Obstacle.Tree;
import GameObject.MapEntity.Obstacle.Water;

import java.util.Random;
/**
 * Represents the LevelMap which contains data about objects in the game.
 *
 * @author Yiming Lu
 */
public class LevelMap {

    private Coordinate[][] tableData = new Coordinate[Coordinate.tableRows][Coordinate.tableColumns];
    private int chestNumber = 10;
    private int enemyNumber = 10;
    private int npcNumber = 10;
    private int rockNumber = 10;
    private int treeNumber = 10;
    private int waterNumber = 10;
    private int currentLevel = 0;
    private Random random = new Random();
    /**
     * Constructs a new LevelMap for the given level and seeds it with entities.
     *
     * @param level the game level for this map.
     */
    public LevelMap(int level) {
        currentLevel = level;
        Seeding(level);
    }
    /**
     * Seeds the game map with entities based on the level.
     *
     * @param level the game level to seed the map for.
     */
    public void Seeding(int level) {
        // Generate borders
        for (int i = 0; i < Coordinate.tableRows; i++) {
            for (int j = 0; j < Coordinate.tableColumns; j++) {
                if (i == 0 || j == 0 || i == Coordinate.tableRows - 1 || j == Coordinate.tableColumns - 1) {
                    tableData[i][j] = new Border(i, j);
                }
            }
        }

        // Generate chests
        for (int i = 0; i < chestNumber; i++) {
            placeEntityRandomly(new Chest(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns)));
        }

        // Generate enemies
        for (int i = 0; i < enemyNumber; i++) {
            placeEntityRandomly(new Enemy(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns)));
        }

        // Generate NPCs
        for (int i = 0; i < npcNumber; i++) {
            placeEntityRandomly(new NPC(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns)));
        }

        for (int i = 0; i < rockNumber; i++) {
            placeEntityRandomly(new Rock(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns)));
        }

        for (int i = 0; i < treeNumber; i++) {
            placeEntityRandomly(new Tree(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns)));
        }

        for (int i = 0; i < waterNumber; i++) {
            placeEntityRandomly(new Water(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns)));
        }


        // Generate doors based on the level
        switch (level) {
            case 1:
                placeEntityRandomly(new Door(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns), 2));
                break;
            case 2:
                placeEntityRandomly(new Door(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns), 1));
                placeEntityRandomly(new Door(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns), 3));
                break;
            case 3:
                placeEntityRandomly(new Door(random.nextInt(Coordinate.tableRows), random.nextInt(Coordinate.tableColumns), 2));
                break;
        }
    }
    /**
     * Places a given entity at a random location within the game map.
     * The method ensures the entity is placed at an empty spot.
     *
     * @param entity the entity to be placed.
     */
    private void placeEntityRandomly(Coordinate entity) {
        int row, col;
        do {
            row = random.nextInt(Coordinate.tableRows);
            col = random.nextInt(Coordinate.tableColumns);
        } while (tableData[row][col] != null); // Keep looking for an empty spot

        entity.setRow(row);
        entity.setCol(col);
        tableData[row][col] = entity;
    }
    /**
     * Returns the game map with all its entities.
     *
     * @return the game map as a 2D array of Coordinates.
     */
    public Coordinate[][] getMap() {
        return tableData;
    }
}
