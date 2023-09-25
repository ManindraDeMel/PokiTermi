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

    public BattleResultTextBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.resultText = "";
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
        isVisible = true;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void hide() {
        isVisible = false;
    }

    public void render(Terminal terminal) throws IOException {
        if (!isVisible) return;

        // Create a TextCharacter to represent the border
        TextCharacter borderChar = new TextCharacter('█', TextColor.ANSI.BLACK, TextColor.ANSI.WHITE, SGR.BOLD);

        // Draw the border
        for (int row = y; row < y + height; row++) {
            for (int col = x; col < x + width; col++) {
                terminal.setCursorPosition(col, row);
                terminal.putCharacter(borderChar.getCharacter());
            }
        }

        // Render the result text in the center
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

