package GameObject.Text;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TextCharacter;

import java.io.IOException;

public class BattleResultTextBox {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private String resultText;
    private boolean isVisible = false;

    /**
     * Creates a new BattleResultTextBox.
     *
     * @param x      The X-coordinate of the top-left corner of the text box.
     * @param y      The Y-coordinate of the top-left corner of the text box.
     * @param width  The width of the text box.
     * @param height The height of the text box.
     * @author Zhangheng Xu
     */
    public BattleResultTextBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.resultText = "";
    }

    /**
     * Sets the result text to be displayed in the text box and makes it visible.
     *
     * @param resultText The result text to display.
     * @author Zhangheng Xu
     */
    public void setResultText(String resultText) {
        this.resultText = resultText;
        isVisible = true;
    }

    /**
     * Checks if the text box is visible.
     *
     * @return true if the text box is visible, false otherwise.
     * @author Zhangheng Xu
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Hides the text box.
     */
    public void hide() {
        isVisible = false;
    }

    /**
     * Renders the battle result text box on the terminal.
     *
     * @param terminal The terminal on which to render the text box.
     * @throws IOException If an I/O error occurs during rendering.
     * @author Zhangheng Xu
     */
    public void render(Terminal terminal) throws IOException {
        if (!isVisible) return;

        drawBorder(terminal);
        renderResultText(terminal);
    }

    /**
     * Draws the border of the text box with a specified character.
     *
     * @param terminal The terminal on which to draw the border.
     * @throws IOException If an I/O error occurs during drawing.
     * @author Zhangheng Xu
     */
    private void drawBorder(Terminal terminal) throws IOException {

        for (int row = y; row < y + height; row++) {
            for (int col = x; col < x + width; col++) {
                terminal.setCursorPosition(col, row);
                terminal.putCharacter('█');
            }
        }
    }

    /**
     * Renders the result text in the center of the text box.
     *
     * @param terminal The terminal on which to render the text.
     * @throws IOException If an I/O error occurs during rendering.
     * @author Zhangheng Xu
     */
    private void renderResultText(Terminal terminal) throws IOException {
        int textX = x + width / 2 - resultText.length() / 2;
        int textY = y + height / 2;

        terminal.setForegroundColor(TextColor.ANSI.WHITE);
        terminal.setBackgroundColor(TextColor.ANSI.BLACK);
        terminal.enableSGR(SGR.BOLD);

        for (int i = 0; i < resultText.length(); i++) {
            terminal.setCursorPosition(textX + i, textY);
            terminal.putCharacter(resultText.charAt(i));
        }

        terminal.disableSGR(SGR.BOLD);
    }


}

