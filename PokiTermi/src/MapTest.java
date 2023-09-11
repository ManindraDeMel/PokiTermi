import GameObject.MapEntity.Coordinate.Coordinate;
import GameObject.MapEntity.Obstacle.Border;
import GameObject.MapEntity.Obstacle.Rock;
import GameObject.MapEntity.Obstacle.Tree;
import GameObject.MapEntity.Obstacle.Water;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapTest {
    public static int tableRows = Coordinate.tableRows;
    public static int tableColumns = Coordinate.tableColumns;
    public static void mapTest(){
        Coordinate tableData [][] = new Coordinate[tableRows][tableColumns];
        tableData = tableLoader();
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

    public static Coordinate[][] tableLoader(){
        Coordinate tableData[][] = new Coordinate[tableRows][tableColumns];

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
}
