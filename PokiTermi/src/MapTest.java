import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Cursor;
import GameObject.MapEntity.Obstacle.Border;
import GameObject.MapEntity.Obstacle.Rock;
import GameObject.MapEntity.Obstacle.Tree;
import GameObject.MapEntity.Obstacle.Water;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MapTest {
    public static int tableRows = Coordinate.tableRows;
    public static int tableColumns = Coordinate.tableColumns;
    static Coordinate[][] tableData = new Coordinate[tableRows][tableColumns];

    static Cursor cursor = new Cursor(5,5);
    public static void mapTest(){

        tableData = tableLoader();
        addPlayer();

        while (true){
            clearScreen();
            display();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "W":
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
                case "S":
                    if(cursor.getRow()<=tableColumns-1&&tableData[cursor.getRow()+1][cursor.getCol()]==null){
                        tableData[cursor.getRow()][cursor.getCol()]=null;
                        cursor.moveDown();
                        tableData[cursor.getRow()][cursor.getCol()]=cursor;
                    }else if(cursor.getRow()<=tableColumns-1&&tableData[cursor.getRow()+1][cursor.getCol()].isAccessible()){
                        tableData[cursor.getRow()][cursor.getCol()]=null;
                        cursor.moveDown();
                        tableData[cursor.getRow()][cursor.getCol()]=cursor;
                    }
                    break;


                case "A":
                    if(cursor.getCol()==1||!tableData[cursor.getRow()][cursor.getCol()-1].isAccessible()){

                    }else {
                        tableData[cursor.getRow()][cursor.getCol()]=null;
                        cursor.moveLeft();
                        tableData[cursor.getRow()][cursor.getCol()]=cursor;
                    }
                    break;
                case "D":
                    if(cursor.getCol()==tableColumns-1||!tableData[cursor.getRow()][cursor.getCol()+1].isAccessible()){

                    }else {
                        tableData[cursor.getRow()][cursor.getCol()]=null;
                        cursor.moveRight();
                        tableData[cursor.getRow()][cursor.getCol()]=cursor;
                    }
                    break;

                case "Q":  // Add a way to quit the loop
                    return;
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

    public static void display(){
        for (int i = 0; i < tableRows; i++) {
            for (int j = 0; j < tableColumns; j++) {
                if (tableData[i][j] != null) {
                    tableData[i][j].display();
                } else {
                    System.out.print(" ");  // Print a space for null elements
                }
            }
            System.out.println();  // Move to the next line after each row
        }
    }

    public static void addPlayer(){

        tableData[cursor.getRow()][cursor.getCol()]=cursor;
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }



}
