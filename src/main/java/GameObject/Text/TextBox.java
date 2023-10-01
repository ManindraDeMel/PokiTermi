package GameObject.Text;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

/**
 * A class for rendering and displaying text boxes in a terminal.
 */
public class TextBox {
    private final int x;       // The X-coordinate of the top-left corner of the text box.
    private final int y;       // The Y-coordinate of the top-left corner of the text box.
    private final int width;   // The width of the text box.
    private final int height;  // The height of the text box.
    private String text;       // The text content to display within the text box.

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    /**
     * Constructs a new TextBox with the specified position and dimensions.
     *
     * @param x      The X-coordinate of the top-left corner of the text box.
     * @param y      The Y-coordinate of the top-left corner of the text box.
     * @param width  The width of the text box.
     * @param height The height of the text box.
     * @author Zhangheng Xu
     */
    public TextBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "";
    }

    /**
     * Sets the text content of the text box.
     *
     * @param text The text to set in the text box.
     *  @author Zhangheng Xu
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Renders the text box on the provided terminal.
     *
     * @param terminal The terminal on which to render the text box.
     * @throws IOException If an I/O error occurs while rendering.
     * @author Zhangheng Xu
     */
    public void render(Terminal terminal) throws IOException {

        // Draw the top border
        for (int i = x; i < x + width; i++) {
            terminal.setCursorPosition(i, y);
            terminal.putCharacter('═'); // Horizontal border character
        }

        // Draw the bottom border
        for (int i = x; i < x + width; i++) {
            terminal.setCursorPosition(i, y + height - 1);
            terminal.putCharacter('═'); // Horizontal border character
        }

        // Draw the left border
        for (int i = y + 1; i < y + height - 1; i++) {
            terminal.setCursorPosition(x, i);
            terminal.putCharacter('║'); // Vertical border character
        }

        // Draw the right border
        for (int i = y + 1; i < y + height - 1; i++) {
            terminal.setCursorPosition(x + width - 1, i);
            terminal.putCharacter('║'); // Vertical border character
        }

        // Draw the corners
        terminal.setCursorPosition(x, y);
        terminal.putCharacter('╔'); // Top-left corner
        terminal.setCursorPosition(x + width - 1, y);
        terminal.putCharacter('╗'); // Top-right corner
        terminal.setCursorPosition(x, y + height - 1);
        terminal.putCharacter('╚'); // Bottom-left corner
        terminal.setCursorPosition(x + width - 1, y + height - 1);
        terminal.putCharacter('╝'); // Bottom-right corner

        // Split the text into lines
        String[] lines = text.split("\n");

        // Render the text, handling line wrapping
        for (int i = 0; i < Math.min(lines.length, height - 2); i++) {
            String line = lines[i];
            int currentX = x + 1;
            int currentY = y + i + 1;
            for (char c : line.toCharArray()) {
                if (currentX < x + width - 1) {
                    terminal.setCursorPosition(currentX, currentY);
                    terminal.putCharacter(c);
                    currentX++;
                } else {
                    // Wrap to the next line if the current line is full
                    currentX = x + 1;
                    currentY++;
                    if (currentY >= y + height - 1) {
                        // The text box is full, no more text can be displayed
                        break;
                    }
                    terminal.setCursorPosition(currentX, currentY);
                    terminal.putCharacter(c);
                    currentX++;
                }
            }
        }
        terminal.flush();
    }
    /**
     * Clears the text content of the text box and renders an empty box on the provided terminal.
     *
     * @param terminal The terminal on which to clear the text box.
     * @throws IOException If an I/O error occurs while clearing.
     * @author Manindra de Mel
     */
    public void clear(Terminal terminal) throws IOException {
        this.text = "";  // Clear the text content

        // Clear the text box interior
        for (int i = x + 1; i < x + width - 1; i++) {
            for (int j = y + 1; j < y + height - 1; j++) {
                terminal.setCursorPosition(i, j);
                terminal.putCharacter(' ');  // Fill with space character to clear
            }
        }

        terminal.flush();  // Reflect the changes to the terminal
    }
}