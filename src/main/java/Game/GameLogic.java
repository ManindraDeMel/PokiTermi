package Game;

import GameObject.Item.Item;
import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Interactive.Door;
import GameObject.MapEntity.PlayerMapCursor;
import GameObject.MapEntity.Interactive.Chest;
import GameObject.MapEntity.Interactive.Enemy;
import GameObject.MapEntity.Interactive.NPC;
import GameObject.MapEntity.Obstacle.Border;
import GameObject.MapEntity.Obstacle.Rock;
import GameObject.MapEntity.Obstacle.Tree;
import GameObject.MapEntity.Obstacle.Water;
import GameObject.Player.Player;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class GameLogic {

    public static int tableRows = Coordinate.tableRows;
    public static int tableColumns = Coordinate.tableColumns;
    public static Coordinate[][] tableData = new Coordinate[tableRows][tableColumns];

    public static Terminal terminal = Game.GameLayout.getTerminal();
    public static PlayerMapCursor playerMapCursor;
    private static Random random = new Random();
    public static Player player = new Player();

    public static int currentLevel = 1;
    public static int previousLevel = 1;
    public static ArrayList<Coordinate[][]> levelMaps = new ArrayList<>();

    public static void initialize() {
        player.setName("Tester");
        levelMaps.add(new LevelMap(1).getMap());
        levelMaps.add(new LevelMap(2).getMap());
        levelMaps.add(new LevelMap(3).getMap());
        tableData = levelMaps.get(0);
        addPlayer();
    }

    public static void runGame() throws IOException {
        initialize();

        while (true) {
            GameLayout.clearScreen();
            GameLayout.displayMap();
            GameLayout.describeEnvironment();

            GameLayout.updateTitleBox();
            GameLayout.updateToolTipBox();

            KeyStroke keyStroke = GameLayout.getTerminal().readInput();
            handleInput(keyStroke);

            if (keyStroke.getCharacter() == 'Q') return;
        }
    }

    public static Coordinate[][] tableLoader(){
        try (BufferedReader br = new BufferedReader(new FileReader("src/Map.conf"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String type = parts[0];

                for (int i = 1; i < parts.length; i++) {
                    String[] coordinates = parts[i].split(",");
                    int row = Integer.parseInt(coordinates[0]);
                    int col = Integer.parseInt(coordinates[1]);

                    switch (type) {
                        case "Border" -> tableData[row][col] = new Border(row, col);
                        case "Rock" -> tableData[row][col] = new Rock(row, col);
                        case "Tree" -> tableData[row][col] = new Tree(row, col);
                        case "Water" -> tableData[row][col] = new Water(row, col);
                        case "NPC" -> tableData[row][col] = new NPC(row, col);
                        case "Chest" -> tableData[row][col] = new Chest(row, col);
                        case "Enemy" -> tableData[row][col] = new Enemy(row, col);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tableData;

    }

    public static void handleInput(KeyStroke keyStroke) throws IOException {
        if (keyStroke == null) return; // Ignore null key strokes.

        // Quit Game
        if (keyStroke.getCharacter() != null && keyStroke.getCharacter() == 'Q') return;

        // Movement
        if (keyStroke.getCharacter() != null) {
            switch (keyStroke.getCharacter()) {
                case 'W':
                case 'w':
                case 'A':
                case 'a':
                case 'S':
                case 's':
                case 'D':
                case 'd':
                    movePlayer(keyStroke.getCharacter());
                    break;
                // Add more cases for other inputs, like interactions, inventory, etc.
            }
        }
    }


    private static void interactAndMove(int dx, int dy) throws IOException {
        int newRow = playerMapCursor.getRow() + dy;
        int newCol = playerMapCursor.getCol() + dx;

        // Check if the new position is within the map boundaries
        if (newRow >= 0 && newRow < tableRows && newCol >= 0 && newCol < tableColumns) {
            Coordinate entity = tableData[newRow][newCol];

            // If there's a chest at the new position
            if (entity instanceof Chest) {
                Chest chest = (Chest) entity;
                Item item = chest.open();
                player.addItem(item);  // Assuming you have a player instance available
                tableData[newRow][newCol] = null;  // Remove the chest from the map


                //debug
                for(var v:player.getInventory()){
                    System.out.println(v.getName());
                }

            }


            if(entity instanceof Door ){

                int tempCol=playerMapCursor.getCol();
                int tempRow=playerMapCursor.getRow();
                tableData[tempRow][tempCol]=null;
                previousLevel = currentLevel;
                currentLevel=((Door) entity).getDestinationLevel();
                tableData = levelMaps.get(currentLevel-1);
                putPlayer();

            }

            // Check if the new position is accessible
            if (entity == null || entity.isAccessible()) {
                tableData[playerMapCursor.getRow()][playerMapCursor.getCol()] = null;
                playerMapCursor.moveTo(newRow, newCol);
                tableData[newRow][newCol] = playerMapCursor;
            }
        }
    }

    public static void movePlayer(char direction) throws IOException {
        switch (direction) {
            case 'W':
            case 'w':
                interactAndMove(0, -1); // Move Up
                break;
            case 'S':
            case 's':
                interactAndMove(0, 1);  // Move Down
                break;
            case 'A':
            case 'a':
                interactAndMove(-1, 0); // Move Left
                break;
            case 'D':
            case 'd':
                interactAndMove(1, 0);  // Move Right
                break;
        }
    }

    public static void addPlayer(){
        int row, col;
        do {
            row = random.nextInt(Coordinate.tableRows);
            col = random.nextInt(Coordinate.tableColumns);
        } while (tableData[row][col] != null); // Keep looking for an empty spot
        playerMapCursor = new PlayerMapCursor(row,col);
        tableData[playerMapCursor.getRow()][playerMapCursor.getCol()]= playerMapCursor;
    }
    public static void putPlayer() throws IOException {
        // Find the door
        for (int i = 0; i < tableRows; i++) {
            for (int j = 0; j < tableColumns; j++) {
                if (tableData[i][j] != null && tableData[i][j] instanceof Door
                        && ((Door) tableData[i][j]).getDestinationLevel() == previousLevel) {

                    // Check above
                    if (i > 0 && tableData[i-1][j] == null) {
                        playerMapCursor = new PlayerMapCursor(i-1, j);
                        tableData[i-1][j] = playerMapCursor;
                        tableData[i-1][j].display();

                        // Check below
                    } else if (i < tableRows-1 && tableData[i+1][j] == null) {
                        playerMapCursor = new PlayerMapCursor(i+1, j);
                        tableData[i+1][j] = playerMapCursor;
                        tableData[i+1][j].display();

                        // Check left
                    } else if (j > 0 && tableData[i][j-1] == null) {
                        playerMapCursor = new PlayerMapCursor(i, j-1);
                        tableData[i][j-1] = playerMapCursor;
                        tableData[i][j-1].display();

                        // Check right
                    } else if (j < tableColumns-1 && tableData[i][j+1] == null) {
                        playerMapCursor = new PlayerMapCursor(i, j+1);
                        tableData[i][j+1] = playerMapCursor;
                        tableData[i][j+1].display();

                    } else {
                        // All spots around the door are occupied
                        System.err.println("No available spot to place the player near the door at (" + i + "," + j + ")");
                        continue; // This will continue with the next iteration of the inner loop.
                    }

                    terminal.flush();
                    return; // This will exit the method after placing the player by the first matching door
                }
            }
        }
    }


    public static String describeEnvironment() throws IOException {
        // Combine showItemAround() and getDirectionalDescription()
        StringBuilder explanation = new StringBuilder();
        explanation.append(getDirectionalDescription(
                tableData[playerMapCursor.getRow() - 1][playerMapCursor.getCol()], "north"));
        explanation.append(getDirectionalDescription(
                tableData[playerMapCursor.getRow() + 1][playerMapCursor.getCol()], "south"));
        explanation.append(getDirectionalDescription(
                tableData[playerMapCursor.getRow()][playerMapCursor.getCol() - 1], "west"));
        explanation.append(getDirectionalDescription(
                tableData[playerMapCursor.getRow()][playerMapCursor.getCol() + 1], "east"));
        return explanation.toString();
    }

    private static String getDirectionalDescription(Coordinate coord, String direction) throws IOException {
        if (coord != null) {
            if (coord instanceof Rock) {
                return "You find a Rock at " + direction + ".\n";
            } else if (coord instanceof Water) {
                return "You find Water at " + direction + ".\n";
            } else if (coord instanceof Tree) {
                return "You find a Tree at " + direction + ".\n";
            } else if (coord instanceof Enemy) {
                return "You find an Enemy at " + direction + ".\n";
            } else if (coord instanceof Chest) {
                return "You find a Chest at " + direction + ".\n";
            } else if (coord instanceof NPC) {
                return "You find an NPC at " + direction + ".\n";
            } else if (coord instanceof Door) {
                return "You find an Door to Level to "+((Door) coord).getDestinationLevel()+" at " + direction + ".\n";
            }
        }
        terminal.setForegroundColor(TextColor.ANSI.DEFAULT); // Reset color
        return "";
    }
}
