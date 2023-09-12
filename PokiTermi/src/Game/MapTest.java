package Game;

import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Cursor;
import GameObject.MapEntity.Obstacle.Border;
import GameObject.MapEntity.Obstacle.Rock;
import GameObject.MapEntity.Obstacle.Tree;
import GameObject.MapEntity.Obstacle.Water;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MapTest {
    public static int tableRows = Coordinate.tableRows;
    public static int tableColumns = Coordinate.tableColumns;
    public static Coordinate[][] tableData = new Coordinate[tableRows][tableColumns];

    public static Cursor cursor = new Cursor(5,5);


    public static DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    public static Terminal terminal;

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

        tableData = tableLoader();
        addPlayer();

        //lanterna




        while (true){
            clearScreen();
            displayMap();
            KeyStroke keyStroke = terminal.pollInput();
            if(keyStroke != null){
                switch (keyStroke.getCharacter()) {
                    case 'W':
                    case 'w':
                        if(cursor.getRow()>=1&&tableData[cursor.getRow()-1][cursor.getCol()]==null){
                            tableData[cursor.getRow()][cursor.getCol()]=null;
                            cursor.moveUp();
                            tableData[cursor.getRow()][cursor.getCol()]=cursor;
                        }else if(cursor.getRow()>=1&&tableData[cursor.getRow()-1][cursor.getCol()].isAccessible()){
                            tableData[cursor.getRow()][cursor.getCol()]=null;
                            cursor.moveUp();
                            tableData[cursor.getRow()][cursor.getCol()]=cursor;
                        }
                        break;
                    case 'S':
                    case 's':
                        if(cursor.getRow()<=tableRows-1&&tableData[cursor.getRow()+1][cursor.getCol()]==null){
                            tableData[cursor.getRow()][cursor.getCol()]=null;
                            cursor.moveDown();
                            tableData[cursor.getRow()][cursor.getCol()]=cursor;
                        }else if(cursor.getRow()<=tableRows-1&&tableData[cursor.getRow()+1][cursor.getCol()].isAccessible()){
                            tableData[cursor.getRow()][cursor.getCol()]=null;
                            cursor.moveDown();
                            tableData[cursor.getRow()][cursor.getCol()]=cursor;
                        }
                        break;


                    case 'A':
                    case 'a':
                        if(cursor.getCol()>=1&&tableData[cursor.getRow()][cursor.getCol()-1]==null){
                            tableData[cursor.getRow()][cursor.getCol()]=null;
                            cursor.moveLeft();
                            tableData[cursor.getRow()][cursor.getCol()]=cursor;
                        }else if(cursor.getCol()>=1&&tableData[cursor.getRow()][cursor.getCol()-1].isAccessible()){
                            tableData[cursor.getRow()][cursor.getCol()]=null;
                            cursor.moveLeft();
                            tableData[cursor.getRow()][cursor.getCol()]=cursor;
                        }
                        break;
                    case 'D':
                    case 'd':
                        if(cursor.getCol()<=tableColumns-1&&tableData[cursor.getRow()][cursor.getCol()+1]==null){
                            tableData[cursor.getRow()][cursor.getCol()]=null;
                            cursor.moveRight();
                            tableData[cursor.getRow()][cursor.getCol()]=cursor;
                        }else if(cursor.getCol()<=tableColumns-1&&tableData[cursor.getRow()][cursor.getCol()+1].isAccessible()){
                            tableData[cursor.getRow()][cursor.getCol()]=null;
                            cursor.moveRight();
                            tableData[cursor.getRow()][cursor.getCol()]=cursor;
                        }

                        break;

                    case 'Q':  // Add a way to quit the loop
                        return;
                }
            }

        }



    }

    public static Coordinate[][] tableLoader(){


        try (BufferedReader br = new BufferedReader(new FileReader("PokiTermi/src/Map.conf"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String type = parts[0];

                for (int i = 1; i < parts.length; i++) {
                    String[] coordinates = parts[i].split(",");
                    int row = Integer.parseInt(coordinates[0]);
                    int col = Integer.parseInt(coordinates[1]);

                    switch (type) {
                        case "Border":
                            tableData[row][col] = new Border(row, col);
                            break;
                        case "Rock":
                            tableData[row][col] = new Rock(row, col);
                            break;
                        case "Tree":
                            tableData[row][col] = new Tree(row, col);
                            break;
                        case "Water":
                            tableData[row][col] = new Water(row, col);
                            break;
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

        tableData[cursor.getRow()][cursor.getCol()]=cursor;
    }

    public static void clearScreen() throws IOException {
        terminal.clearScreen();
    }






}
