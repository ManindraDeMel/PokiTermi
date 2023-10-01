package GameObject.Item;

import GameObject.Item.BattleItem.BattleItem;
import GameObject.Item.BattleItem.BattleItemType;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.Item.Potion.Potion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private BattleItem battleItem;
    private PokeBall pokeBall;
    private Potion potion;
    private Item genericItem;

    @BeforeEach
    public void setUp() {
        battleItem = new BattleItem(5, BattleItemType.XAttack);
        pokeBall = new PokeBall("GreatBall", 3, PokeBallType.GREATBALL);
        potion = new Potion(4, 100);
        genericItem = new Item("GenericItem", "A generic item for testing.");
    }

    // Test for BattleItem
    @Test
    public void testBattleItemInitialization() {
        assertEquals(5, battleItem.getQuantity());
        assertEquals(BattleItemType.XAttack, battleItem.getType());
        assertEquals(20, battleItem.getBoostAmount());
    }

    // Test for PokeBall
    @Test
    public void testPokeBallInitialization() {
        assertEquals(3, pokeBall.getQuantity());
        assertEquals(PokeBallType.GREATBALL, pokeBall.getType());
    }

    // Test for Potion
    @Test
    public void testPotionInitialization() {
        assertEquals(4, potion.getQuantity());
        assertEquals(100, potion.getHealAmount());
    }

    // Test for generic Item
    @Test
    public void testGenericItemInitialization() {
        assertEquals("GenericItem", genericItem.getName());
        assertEquals("A generic item for testing.", genericItem.getDescription());
    }

    @Test
    public void testGenericItemUse() {
        assertEquals("You used GenericItem.", genericItem.use());
    }

    @Test
    public void testGenericItemToString() {
        assertEquals("GenericItem: A generic item for testing.", genericItem.toString());
    }
}
