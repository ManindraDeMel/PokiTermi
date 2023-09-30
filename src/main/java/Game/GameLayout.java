package Game;

import GameObject.MapEntity.Interactive.NPC;
import GameObject.Text.TextBox;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;



/**
 * GameLayout manages the visual representation of the game, controlling the display of the game map, title, and tooltips.
 * @author Zhangheng Xu
 * @author Manindra de Mel
 * @author Yiming Lu
 */
public class GameLayout {
    public static int TERMINALX = 130;
    public static int TERMINALY = 30;
    private static final DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    private static final Terminal terminal;
    static int titleBoxX = 20;        // X-coordinate
    static int titleBoxY = 0;        // Y-coordinate
    static int titleBoxWidth = 30;   // Width of the title text box
    static int titleBoxHeight = 3;  // Height of the title text box
    static int toolTipBoxX = 50;
    static int toolTipBoxY = 0;
    static int toolTipWidth = 30;
    static int toolTipHeight = 20;
    static int inventoryHeight = 20;
    static int inventoryWidth = 40;
    static int battleX = 20;
    static int battleY = 5;
    static int battleWidth = 30;
    static int battleHeight = 15;
    static int battleResultX = 20;
    static int battleResultY = 2;
    static int battleResultWidth = 30;
    static int battleResultHeight = 3;
    static TextBox resultTextBox = new TextBox(battleResultX, battleResultY, battleResultWidth, battleResultHeight);
    static TextBox battleBox = new TextBox(battleX,battleY,battleWidth,battleHeight);
    static TextBox titleBox = new TextBox(titleBoxX, titleBoxY, titleBoxWidth, titleBoxHeight);
    static TextBox toolTipBox = new TextBox(toolTipBoxX,toolTipBoxY,toolTipWidth,toolTipHeight);
    // Set the text for the textBox
    static String titleBoxText = "****Welcome to PokiTermi****";
    /**
     * @author Zhangheng Xu
     */
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

    static {
        try {
            System.out.println("Initializing terminal..."); // Add this line
            terminalFactory.setInitialTerminalSize(new TerminalSize(TERMINALX, TERMINALY));
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Returns the terminal used for displaying game elements.
     *
     * @return The game terminal.
     * @author Yiming Lu
     */
    public static Terminal getTerminal() {
        return terminal;
    }
    /**
     * Renders the game map on the terminal.
     *
     * @throws IOException if there's an error during rendering.
     * @author Yiming Lu
     * @author Manindra de Mel
     */
    public static void displayMap() throws IOException {
        for (int row = 0; row < GameLogic.tableRows; row++) {
            for (int col = 0; col < GameLogic.tableColumns; col++) {
                terminal.setCursorPosition(col, row);
                if (GameLogic.tableData[row][col] != null) {
                    TextColor objectColor = GameLogic.tableData[row][col].getColor();
                    terminal.setForegroundColor(objectColor);
                    terminal.putCharacter(GameLogic.tableData[row][col].getSymbol());
                } else {
                    terminal.setForegroundColor(TextColor.ANSI.DEFAULT);
                    terminal.putCharacter(' '); // or any other default character for empty spaces
                }
            }
        }
    }

    /**
     * display text result text box
     *
     * @throws IOException if there's an error during rendering.
     * @author Zhangheng Xu
     */
    public static void displayTextResult() throws IOException{
        resultTextBox.setText("---------HelloWorld---------");
        resultTextBox.render(terminal);
    }
    /**
     * Updates the battle box and renders it on the terminal.
     *
     * @throws IOException if there's an error during rendering.
     * @author Zhangheng Xu
     */
    public static void updateBattleBox() throws IOException {
        battleBox.render(terminal);
    }

    /**
     * chose random dialogue when talk to NPC
     *
     * @throws IOException if there's an error during rendering.
     * @author Zhangheng Xu
     */
    public static void startTalk() throws IOException {
        battleBox.setText(NPC.NPCTalk());
        battleBox.render(terminal);
    }
    /**
     * Updates the title box and renders it on the terminal.
     *
     * @throws IOException if there's an error during rendering.
     * @author Manindra de Mel
     */
    public static void updateTitleBox() throws IOException {
        titleBox.setText(titleBoxText);
        titleBox.render(terminal);
    }

    /**
     * Updates the tooltip box and renders it on the terminal.
     *
     * @throws IOException if there's an error during rendering.
     * @author Manindra de Mel
     */
    public static void updateToolTipBox() throws IOException {
        toolTipBox.setText(toolTipBoxText);
        toolTipBox.render(terminal);
    }
    /**
     * Clears the terminal screen.
     *
     * @throws IOException if there's an error clearing the screen.
     * @author Manindra de Mel
     */
    public static void clearScreen() throws IOException {
        terminal.clearScreen();
    }

    public static void displayInventory() throws IOException {
        // Compute the position for the inventory box
        int inventoryBoxX = toolTipBox.getX() + toolTipBox.getWidth();
        int inventoryBoxY = toolTipBox.getY();

         // Initialize the inventory box
        TextBox inventoryBox = new TextBox(inventoryBoxX, inventoryBoxY, inventoryWidth, inventoryHeight);
        inventoryBox.setText("Inventory\n\n\n" + GameLogic.getPlayer().getInventory().toString());
        inventoryBox.render(terminal);
    }
    /**
     * Describes the environment around the player and displays it on the terminal.
     *
     * @throws IOException if there's an error during display.
     * @author Yiming Lu
     */
    public static void describeEnvironment() throws IOException {
        String explanation = GameLogic.describeEnvironment();
        displayMessage(explanation);
    }

    public static void displayMessage(String s) throws IOException {
        terminal.setCursorPosition(0, GameLogic.tableRows + 1);
        for (char ch : s.toCharArray()) {
            terminal.putCharacter(ch);
        }
        terminal.flush();
    }
}
