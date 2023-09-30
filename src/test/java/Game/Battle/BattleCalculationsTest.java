package Game.Battle;

import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.Player.Inventory.Inventory;
import GameObject.Pokemon.Pokemon;
import GameObject.Pokemon.data.PokemonData;
import GameObject.Pokemon.data.Stats;
import GameObject.Pokemon.data.Type.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BattleCalculationsTest {

    private BattleCalculations battleCalculations;
    private Pokemon enemyPokemon;
    private Inventory playerInventory;

    @BeforeEach
    public void setUp() {
        battleCalculations = new BattleCalculations();
        // Example PokemonData and Stats for a Pokemon named "Pikachu"
        PokemonData pikachuData = getPokemonData();

        enemyPokemon = new Pokemon(pikachuData);
        enemyPokemon.setHealth(50);  // Assuming there's a method to set current health

        playerInventory = new Inventory();
        PokeBall pokeBall = new PokeBall("Great Ball", 5, PokeBallType.GREATBALL);  // 5 GREATBALLs
        playerInventory.addInventoryItem(pokeBall);
    }

    private static PokemonData getPokemonData() {
        Stats pikachuStats = new Stats(55, 40, 100);  // Attack, Defense, Max Health
        Type electricType = Type.ELECTRIC;
        List<Type> pikachuTypes = new ArrayList<>();
        pikachuTypes.add(electricType);

        PokemonData pikachuData = new PokemonData(
                25,               // Pokemon ID for Pikachu
                "Pikachu",       // Name
                pikachuTypes,    // Type(s)
                pikachuStats,    // Stats
                Arrays.asList("Static")  // Abilities
        );
        return pikachuData;
    }

    @Test
    public void testCalculateCatchChanceWithValidPokeBall() {
        System.out.println(playerInventory.toString());
        double catchChance = battleCalculations.calculateCatchChance(enemyPokemon, playerInventory, PokeBallType.GREATBALL, 1);
        assertEquals(1.0, catchChance, "Expected a 100% catch chance with an Great ball for a Pokémon with half health");
    }

    @Test
    public void testCalculateCatchChanceWithNotEnoughPokeBalls() {
        assertThrows(IllegalArgumentException.class, () -> battleCalculations.calculateCatchChance(enemyPokemon, playerInventory, PokeBallType.GREATBALL, 10), "Expected an IllegalArgumentException due to insufficient Pokéballs");
    }
}
