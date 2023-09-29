package Game;

import GameObject.Text.TextBox;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class GameLayout {

    private static DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    private static Terminal terminal;

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

    static {
        try {
            System.out.println("Initializing terminal..."); // Add this line
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Terminal getTerminal() {
        return terminal;
    }

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

    public static void updateTitleBox() throws IOException {
        titleBox.setText(titleBoxText);
        titleBox.render(terminal);
    }

    public static void updateToolTipBox() throws IOException {
        toolTipBox.setText(toolTipBoxText);
        toolTipBox.render(terminal);
    }

    public static void clearScreen() throws IOException {
        terminal.clearScreen();
    }

    public static void describeEnvironment() throws IOException {
        String explanation = GameLogic.describeEnvironment();
        // Here, you'll need to update the terminal or any GUI element
        // with the description from the game logic.
        // For the sake of simplicity, just printing to terminal.
        terminal.setCursorPosition(0, GameLogic.tableRows + 1); // Assuming this positions the cursor below the map
        for (char ch : explanation.toCharArray()) {
            terminal.putCharacter(ch);
        }
        terminal.flush();
    }
}
