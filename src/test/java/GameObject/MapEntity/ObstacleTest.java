package GameObject.MapEntity;

import GameObject.MapEntity.Obstacle.Border;
import GameObject.MapEntity.Obstacle.Rock;
import GameObject.MapEntity.Obstacle.Tree;
import GameObject.MapEntity.Obstacle.Water;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {

    private Border border;
    private Rock rock;
    private Tree tree;
    private Water water;

    @BeforeEach
    public void setUp() {
        border = new Border(0, 0);
        rock = new Rock(5, 5);
        tree = new Tree(6, 6);
        water = new Water(7, 7);
    }

    // Test for Border
    @Test
    public void testBorderInitialization() {
        assertEquals(0, border.getRow());
        assertEquals(0, border.getCol());
        assertEquals('\u250C', border.getSymbol()); // Left top corner
    }

    @Test
    public void testBorderSymbolAssignment() {
        Border bottomRightBorder = new Border(Border.tableRows - 1, Border.tableColumns - 1);
        assertEquals('\u2518', bottomRightBorder.getSymbol()); // Right bottom corner
    }

    // Test for Rock
    @Test
    public void testRockInitialization() {
        assertEquals(5, rock.getRow());
        assertEquals(5, rock.getCol());
        assertEquals('O', rock.getSymbol());
    }

    // Test for Tree
    @Test
    public void testTreeInitialization() {
        assertEquals(6, tree.getRow());
        assertEquals(6, tree.getCol());
        assertEquals('F', tree.getSymbol());
    }

    // Test for Water
    @Test
    public void testWaterInitialization() {
        assertEquals(7, water.getRow());
        assertEquals(7, water.getCol());
        assertEquals('~', water.getSymbol());
    }
}
