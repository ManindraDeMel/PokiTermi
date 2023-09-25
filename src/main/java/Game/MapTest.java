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
import GameObject.Text.TextBox;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MapTest {
    public static int tableRows = Coordinate.tableRows;
    public static int tableColumns = Coordinate.tableColumns;
    public static Coordinate[][] tableData = new Coordinate[tableRows][tableColumns];

    public static PlayerMapCursor playerMapCursor ;

    public static DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

    public static Terminal terminal;
    private static Random random = new Random();

    public static Player player=new Player();

    public static int currentLevel=1;
    public static int previousLevel=1;
    public static ArrayList<Coordinate[][]> levelMaps=new ArrayList<>();
    // Define the position (x, y) and dimensions (width, height) of the title box
    static int titleBoxX = 20;        // X-coordinate
    static int titleBoxY = 0;        // Y-coordinate
    static int titleBoxWidth = 30;   // Width of the title text box
    static int titleBoxHeight = 3;  // Height of the title text box
    static int toolTipBoxX = 50;
    static int toolTipBoxY = 0;
    static int toolTipWidth = 30;
    static int toolTipHeight = 20;

    // Create instance of the TextBox class
    static TextBox titleBox = new TextBox(titleBoxX, titleBoxY, titleBoxWidth, titleBoxHeight);
    static TextBox toolTipBox = new TextBox(toolTipBoxX,toolTipBoxY,toolTipWidth,toolTipHeight);

    // Set the text for the textBox
    static String titleBoxText = "****Welcome to PokiTermi****";
    static String toolTipBoxText = "Ohayo,Professor Oak. You got drunk last night and forgot how to move. Don't worry,  here are some tips to move  around.                     " +
            "go north by click 'w'       " +
            "go south by click 's'       " +
            "go west by click 'a'        " +
            "go east by click 'd'        " +
            "----------------------------" +
            "In PokiTermi world, you are " +
            "pink '@', you can find white" +
            " 'O' as rock,green 'F' as   tree, cyan '~' as   water.  " +
            "u can talk with npc blue '@'" +
            "fight with enemy red '@'    " +
            "get item from chest yellow'?" +
            "go to next level by white'D'";



    //where cursor print explaination text

    static {
        try {
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Terminal getTerminal() {
        return terminal;
    }

    public static void runMapTest() throws IOException {

        //tableData = tableLoader();
        player.setName("Tester");


        //lanterna


        levelMaps.add(new LevelMap(1).getMap());
        levelMaps.add(new LevelMap(2).getMap());
        levelMaps.add(new LevelMap(3).getMap());
        tableData = levelMaps.get(0);
        addPlayer();




        while (true){
            clearScreen();
            displayMap();
            showItemAround();


            // set the text box
            titleBox.setText(titleBoxText);
            toolTipBox.setText(toolTipBoxText);
            // Render the textBox
            titleBox.render(terminal);
            toolTipBox.render(terminal);

            KeyStroke keyStroke = terminal.readInput();
            if(keyStroke != null){
                switch (keyStroke.getCharacter()) {
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
                    case 'Q':  // Add a way to quit the loop
                        return;
                }
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

    public static void displayMap() throws IOException {
        for (int i = 0; i < tableRows; i++) {
            for (int j = 0; j < tableColumns; j++) {
                if (tableData[i][j] != null) {
                    tableData[i][j].display();
                } else {
                    terminal.putCharacter(' ');  // Print a space for null elements
                }
            }
            terminal.putCharacter('\n');  // Move to the next line after each row
        }
        terminal.flush();  // Ensure that all changes are pushed to the screen
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
        //find the door
        for (int i = 0; i < tableRows; i++) {
            for (int j = 0; j < tableColumns; j++) {
                if (tableData[i][j] !=null&&tableData[i][j] instanceof Door&& ((Door) tableData[i][j]).getDestinationLevel()==previousLevel){
                    if(tableData[i-1][j]==null){
                        playerMapCursor = new PlayerMapCursor(i-1,j);
                        tableData[i-1][j]=playerMapCursor;
                        tableData[i-1][j].display();
                    }else if(tableData[i+1][j]==null){
                        playerMapCursor = new PlayerMapCursor(i+1,j);
                        tableData[i-1][j]=playerMapCursor;
                        tableData[i+1][j].display();
                    }else if(tableData[i][j-1]==null){
                        playerMapCursor = new PlayerMapCursor(i,j-1);
                        tableData[i-1][j]=playerMapCursor;
                        tableData[i][j-1].display();
                    }else {
                        playerMapCursor = new PlayerMapCursor(i,j+1);
                        tableData[i-1][j]=playerMapCursor;
                        tableData[i][j+1].display();
                    }
                }
            }
        }
        terminal.flush();
    }




    public static void clearScreen() throws IOException {
        terminal.clearScreen();
    }

    public static void showItemAround() throws IOException {
        StringBuilder explanation = new StringBuilder();

        // North
        Coordinate north = tableData[playerMapCursor.getRow() - 1][playerMapCursor.getCol()];
        explanation.append(getDirectionalDescription(north, "north"));

        // South
        Coordinate south = tableData[playerMapCursor.getRow() + 1][playerMapCursor.getCol()];
        explanation.append(getDirectionalDescription(south, "south"));

        // West
        Coordinate west = tableData[playerMapCursor.getRow()][playerMapCursor.getCol() - 1];
        explanation.append(getDirectionalDescription(west, "west"));

        // East
        Coordinate east = tableData[playerMapCursor.getRow()][playerMapCursor.getCol() + 1];
        explanation.append(getDirectionalDescription(east, "east"));

        try {
            terminal.setCursorPosition(0, tableRows + 1);
            String[] lines = explanation.toString().split("\n");
            for (String line : lines) {
                terminal.putString(line);
                System.out.println(line);
                terminal.setCursorPosition(0, terminal.getCursorPosition().getRow() + 1);
            }
            terminal.flush();  // Flush the output
            //terminal.refresh();  // Refresh the terminal
        } catch (IOException e) {
            e.printStackTrace();
        }
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
