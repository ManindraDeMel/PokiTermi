package Game;

import GameObject.Item.BattleItem.BattleItem;
import GameObject.Item.BattleItem.BattleItemType;
import GameObject.Item.Item;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.Item.Potion.Potion;
import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Interactive.Door;
import GameObject.MapEntity.PlayerMapCursor;
import GameObject.MapEntity.Interactive.Chest;
import GameObject.MapEntity.Interactive.Enemy;
import GameObject.MapEntity.Interactive.NPC;
import GameObject.MapEntity.Obstacle.Rock;
import GameObject.MapEntity.Obstacle.Tree;
import GameObject.MapEntity.Obstacle.Water;
import GameObject.Player.Inventory.InventoryItem;
import GameObject.Player.Player;
import GameObject.Pokemon.ActionResult;
import GameObject.Pokemon.Battle;
import GameObject.Pokemon.Pokemon;
import GameObject.Text.TextBox;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static Game.GameLayout.displayInventory;

/**
 * GameLogic is the primary class responsible for running and managing the state of the game.
 * It initializes game elements, handles user inputs, and manages game progression.
 *
 * @author Manindra de Mel (refactored class structure)
 * @author Yiming Lu (wrote methods)
 */
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
    static int battleX = 20;
    static int battleY = 5;
    static int battleWidth = 30;
    static int battleHeight = 15;
    static TextBox battleBox = new TextBox(battleX,battleY,battleWidth,battleHeight);
    public static ArrayList<Coordinate[][]> levelMaps = new ArrayList<>();

    /**
     * Initializes the game by setting up the player, loading level maps, and placing the player on the map.
     *
     * @author Yiming Lu
     */
    public static void initialize() {
        player.setName("Tester");
        levelMaps.add(new LevelMap(1).getMap());
        levelMaps.add(new LevelMap(2).getMap());
        levelMaps.add(new LevelMap(3).getMap());
        tableData = levelMaps.get(0);
        addPlayer();
    }

    /**
     * Main game loop which continually updates and displays the game state until the player decides to quit.
     *
     * @throws IOException if any I/O error occurs.
     * @author Yiming Lu
     * @author Manindra de Mel
     */
    public static void runGame() throws IOException {
        initialize();

        while (true) {
            GameLayout.clearScreen();
            GameLayout.displayMap();
            GameLayout.describeEnvironment();
            GameLayout.updateTitleBox();
            GameLayout.updateToolTipBox();
            GameLayout.displayInventory();
            updateBattleBox();
            KeyStroke keyStroke = GameLayout.getTerminal().readInput();
            handleInput(keyStroke);
            if (keyStroke.getCharacter() == 'Q') return;
        }
    }
    /**
     * Handles player input to control the game.
     *
     * @param keyStroke The keystroke input from the player.
     * @throws IOException if any I/O error occurs.
     * @author Yiming Lu
     */
    public static void handleInput(KeyStroke keyStroke) throws IOException {
        if (keyStroke == null) return; // Ignore null key strokes.

        // Quit Game
        if (keyStroke.getCharacter() != null && keyStroke.getCharacter() == 'Q') return;
        // inventory
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'i') {
            System.out.println("Attempting to display inventory...");  // Debug statement
            displayInventory();
        }

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
    /**
     * Processes player movement and interaction based on direction.
     *
     * @param dx The x-direction movement (-1 for left, 1 for right).
     * @param dy The y-direction movement (-1 for up, 1 for down).
     * @throws IOException if any I/O error occurs.
     * @author Yiming Lu
     * @author Zhangheng Xu
     */
    private static void interactAndMove(int dx, int dy,KeyStroke keyStroke) throws IOException {
        int newRow = playerMapCursor.getRow() + dy;
        int newCol = playerMapCursor.getCol() + dx;

        // Check if the new position is within the map boundaries
        if (newRow >= 0 && newRow < tableRows && newCol >= 0 && newCol < tableColumns) {
            Coordinate entity = tableData[newRow][newCol];

            // If there's a chest at the new position
            if (entity instanceof Chest) {
                Chest chest = (Chest) entity;
                Item item = chest.open();
                player.getInventory().addInventoryItem((InventoryItem) item);  // Assuming you have a player instance available
                tableData[newRow][newCol] = null;  // Remove the chest from the map
            }

            //If there's an enemy at the new position, start a battle.
            if(entity instanceof Enemy){
                Pokemon enemy = ((Enemy) entity).open();
                startBattle(enemy,keyStroke);
                tableData[newRow][newCol] = null;  // Remove the enemy from the map
            }

            //if there is NPC, start to talk
            if(entity instanceof NPC){
                startTalk();
                tableData[newRow][newCol] = null;  // Remove the enemy from the map
            }


            if(entity instanceof Door){

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
    /**
     * Updates the battle box and renders it on the terminal.
     *
     * @throws IOException if there's an error during rendering.
     * @author Zhangheng Xu
     */
    public static void updateBattleBox() throws IOException {
        battleBox.setText("adada");
        battleBox.render(terminal);

    }
    public static void startTalk() throws IOException {
        // Display NPC's introduction text in the battleBox
            battleBox.setText("good morning");
            battleBox.render(terminal);

    }
    public static void startBattle(Pokemon enemy, KeyStroke keyStroke) throws IOException {
        // initialization
        BattleItem battleItem = null;
        PokeBall pokeball = null;
        Potion potion = null;
        // test
        Pokemon pokemon = new Pokemon(100, 50, 50);
        battleItem = new BattleItem(1, BattleItemType.XAttack);  // Initialize battleItem
        pokeball = new PokeBall("helloWorld", 1, PokeBallType.NORMALBALL);  // Initialize pokeball
        potion = new Potion(1);  // Initialize potion

        // Update the battleBox text to indicate the start of the battle
        battleBox.setText("You meet an enemy!");
        battleBox.render(terminal);

        List<String> questionsList = collectBattleQuestions(keyStroke);
        Battle battle = new Battle(pokemon, enemy, battleItem, pokeball, potion);
        ActionResult battleResult = battle.battleResult();

        if (battleResult.equals(ActionResult.CAPTURE)) {
            battleBox.setText("You caught a new pokemon!");
            // Add the new pokemon into the player's inventory.
        } else if (battleResult.equals(ActionResult.VICTORY)) {
            battleBox.setText("Victory");
        } else {
            battleBox.setText("Defeat");
        }
    }

    /**
     * Private method to collect battle-related questions from the user and return them as a List of Strings.
     *
     *
     * @return A List containing battle-related questions:
     *         - Element 0: Pokemon index
     *         - Element 1: Battle item ('a' for attack, 'd' for defense, 'n' for none)
     *         - Element 2: Pokeball ('b' for normal, 'g' for great, 'n' for none)
     *         - Element 3: Potion ('y' for yes, 'n' for no)
     * @author Zhangheng Xu
     */

    private static List<String> collectBattleQuestions(KeyStroke keyStroke) {
        List<String> questions = new ArrayList<>();

        // Collecting user input for each question
        if (keyStroke != null && keyStroke.getCharacter() == '1') {
            questions.add(0, "1");
        }
        // Add more logic to collect other questions based on user input

        return questions;
    }



        /**
         * Moves the player character in the given direction.
         *
         * @param direction A character representing the direction to move ('W' for up, 'A' for left, etc.).
         * @throws IOException if any I/O error occurs.
         * @author Yiming Lu
         */
    public static void movePlayer(char direction) throws IOException {
        switch (direction) {
            case 'W':
            case 'w':
                interactAndMove(0, -1,null); // Move Up
                break;
            case 'S':
            case 's':
                interactAndMove(0, 1,null);  // Move Down
                break;
            case 'A':
            case 'a':
                interactAndMove(-1, 0,null); // Move Left
                break;
            case 'D':
            case 'd':
                interactAndMove(1, 0,null);  // Move Right
                break;
        }
    }
    /**
     * Randomly places the player on the map where no object or entity exists.
     *
     * @author Yiming Lu
     */
    public static void addPlayer(){
        int row, col;
        do {
            row = random.nextInt(Coordinate.tableRows);
            col = random.nextInt(Coordinate.tableColumns);
        } while (tableData[row][col] != null); // Keep looking for an empty spot
        playerMapCursor = new PlayerMapCursor(row,col);
        tableData[playerMapCursor.getRow()][playerMapCursor.getCol()]= playerMapCursor;
    }
    /**
     * Places the player near a door that matches the previous level.
     *
     * @throws IOException if any I/O error occurs.
     * @author Yiming Lu
     */
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

    public static Player getPlayer() {
        return player;
    }

    /**
     * Provides a description of the environment surrounding the player.
     *
     * @return A string describing objects/entities in the four cardinal directions from the player.
     * @throws IOException if any I/O error occurs.
     * @author Yiming Lu
     */
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
    /**
     * Generates a description for a given Coordinate in a specific direction.
     *
     * @param coord The Coordinate being described.
     * @param direction The direction (e.g., "north", "south") in which the Coordinate is located relative to the player.
     * @return A string description of the Coordinate.
     * @throws IOException if any I/O error occurs.
     * @author Yiming Lu
     */
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
