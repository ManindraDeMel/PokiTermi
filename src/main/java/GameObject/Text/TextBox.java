package GameObject.Text;

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
        // Clear the text box area
        for (int row = y; row < y + height; row++) {
            for (int col = x; col < x + width; col++) {
                terminal.setCursorPosition(col, row);
                terminal.putCharacter(' ');
            }
        }

        // Render the text
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length && i < height; i++) {
            String line = lines[i];
            terminal.setCursorPosition(x, y + i);
            terminal.putString(line);
        }

        // Flush the terminal to display changes
        terminal.flush();
    }
}