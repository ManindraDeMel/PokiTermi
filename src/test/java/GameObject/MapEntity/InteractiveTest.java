package GameObject.MapEntity;

import GameObject.MapEntity.Interactive.Chest;
import GameObject.MapEntity.Interactive.Enemy;
import GameObject.MapEntity.Interactive.NPC;
import GameObject.MapEntity.Interactive.Door;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InteractiveTest {

    private Chest chest;
    private Enemy enemy;
    private NPC npc;
    private Door door;

    @BeforeEach
    public void setUp() {
        chest = new Chest(5, 5);
        enemy = new Enemy(6, 6);
        npc = new NPC(7, 7);
        door = new Door(8, 8, 2);
    }

    // Test for Chest
    @Test
    public void testChestInitialization() {
        assertEquals(5, chest.getRow());
        assertEquals(5, chest.getCol());
        assertEquals('?', chest.getSymbol());
    }

    @Test
    public void testOpenChest() throws Exception {
        assertNotNull(chest.open());
    }

    // Test for Enemy
    @Test
    public void testEnemyInitialization() {
        assertEquals(6, enemy.getRow());
        assertEquals(6, enemy.getCol());
        assertEquals('@', enemy.getSymbol());
    }

    // Test for NPC
    @Test
    public void testNPCInitialization() {
        assertEquals(7, npc.getRow());
        assertEquals(7, npc.getCol());
        assertEquals('@', npc.getSymbol());
    }

    @Test
    public void testNPCTalk() throws Exception {
        String dialogue = NPC.NPCTalk();
        assertNotNull(dialogue);
        assertFalse(dialogue.isEmpty());
    }

    // Test for Door
    @Test
    public void testDoorInitialization() {
        assertEquals(8, door.getRow());
        assertEquals(8, door.getCol());
        assertEquals('D', door.getSymbol());
        assertEquals(2, door.getDestinationLevel());
    }

    @Test
    public void testDoorDestination() {
        Door doorToLevel1 = new Door(9, 9, 1);
        assertEquals(1, doorToLevel1.getDestinationLevel());
    }
}