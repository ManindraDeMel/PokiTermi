package Game.Battle;

import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.Player.Inventory.Inventory;
import GameObject.Player.Inventory.InventoryItem;
import GameObject.Pokemon.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BattleCalculationsTest {

    private BattleCalculations battleCalculations;
    private Pokemon enemyPokemon;
    private Inventory playerInventory;

    @BeforeEach
    public void setUp() {
        battleCalculations = new BattleCalculations();

        // Example: A Pokémon with a maximum health of 100 and current health of 50
        enemyPokemon = new Pokemon();
        enemyPokemon.setHealth(100);

        playerInventory = new Inventory();
        PokeBall pokeBall = new PokeBall("Great Ball", 5, PokeBallType.GREATBALL);  // 5 GREATBALLs
        playerInventory.addInventoryItem(pokeBall);
    }

    @Test
    public void testCalculateCatchChanceWithValidPokeBall() {
        PokeBall pokeBall = new PokeBall("Great Ball", 1, PokeBallType.GREATBALL);
        double catchChance = battleCalculations.calculateCatchChance(enemyPokemon, playerInventory, pokeBall, 1);
        assertEquals(1.0, catchChance, "Expected a 100% catch chance with an UltraBall for a Pokémon with half health");
    }

    @Test
    public void testCalculateCatchChanceWithNotEnoughPokeBalls() {
        PokeBall pokeBall = new PokeBall("Great Ball", 10, PokeBallType.GREATBALL);  // Requesting 10 UltraBalls but only have 5
        assertThrows(IllegalArgumentException.class, () -> battleCalculations.calculateCatchChance(enemyPokemon, playerInventory, pokeBall, 10), "Expected an IllegalArgumentException due to insufficient Pokéballs");
    }
}
