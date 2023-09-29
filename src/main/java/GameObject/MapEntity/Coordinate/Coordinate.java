package GameObject.MapEntity.Coordinate;
import Game.GameLayout;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Coordinate implements CoordinateInterface{
    private int row;
    private int col;
    private boolean isAccessible=false;
    private Character symbol='?';

    private TextColor color;

    public TextColor getColor() {
        return color;
    }

    public void setColor(TextColor color) {
        this.color = color;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public static int tableRows = 20;
    public static int tableColumns = 20;
    public Coordinate(int row,int col){
        this.row=row;
        this.col=col;
    }

    public boolean isSame(Coordinate coordinate) {
        return this.row == coordinate.getRow() && this.col == coordinate.getCol();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void display() {
        Terminal terminal = GameLayout.getTerminal(); // Assuming you have a static getter for the terminal in Game.MapTest
        if (this.color == null) {
            this.color = TextColor.ANSI.DEFAULT;
        }
        try {
            terminal.setCursorPosition(this.getCol(), this.getRow());
            terminal.setForegroundColor(this.color);
            terminal.putCharacter(this.symbol);
            terminal.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }
}
