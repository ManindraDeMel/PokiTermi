package Game.Battle;

import GameObject.Item.BattleItem.BattleItemType;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.Item.Potion.Potion;
import GameObject.Player.Inventory.Inventory;
import GameObject.Pokemon.Pokemon;
import GameObject.Player.Inventory.InventoryItem;
import GameObject.Pokemon.data.Type.Type;
import GameObject.Pokemon.data.Type.TypeEffectiveness;

/**
 * Provides utility methods for calculating outcomes in battles based on Pokémon and their stats,
 * type advantages, and the player's inventory.
 * @author Manindra de Mel
 */
public class BattleCalculations {

    private static final double BASE_WIN_CHANCE = 0.5;
    private static final double INVENTORY_BOOST = 0.1;
    /**
     * Calculates the type effectiveness multiplier based on the Pokémon's types.
     *
     * @param attacker The attacking Pokémon.
     * @param defender The defending Pokémon.
     * @return The type effectiveness multiplier.
     */
    private double calculateTypeEffectivenessMultiplier(Pokemon attacker, Pokemon defender) {
        double multiplier = 1.0;

        for (Type defenseType : defender.getType()) {
            TypeEffectiveness effectiveness = attacker.getTypeEffectiveness();

            if (effectiveness.getSuperEffective().contains(defenseType)) {
                multiplier *= 2.0;
            } else if (effectiveness.getNotVeryEffective().contains(defenseType)) {
                multiplier *= 0.5;
            } // No need for "no effect" since it multiplies by 1
        }

        // Safeguard to ensure multiplier is within bounds.
        return Math.max(0.25, Math.min(multiplier, 4.0));
    }
    /**
     * Calculates the chance of the player's Pokémon winning against an enemy Pokémon based solely on stats and type advantages.
     *
     * @param playerPokemon The player's Pokémon.
     * @param enemyPokemon The enemy Pokémon.
     * @return The win chance as a decimal (e.g., 0.75 for a 75% chance).
     */
    public double calculateWinChance(Pokemon playerPokemon, Pokemon enemyPokemon) {
        double typeEffectivenessMultiplier = calculateTypeEffectivenessMultiplier(playerPokemon, enemyPokemon);

        // Calculate the power score for both Pokémon
        double playerPower = playerPokemon.getAttackValue() + playerPokemon.getDefenseValue() + 2 * playerPokemon.getHealth();
        double enemyPower = enemyPokemon.getAttackValue() + enemyPokemon.getDefenseValue() + 2 * enemyPokemon.getHealth();

        // Adjust the win chance based on relative power
        double powerAdjustedWinChance = BASE_WIN_CHANCE * (playerPower / (playerPower + enemyPower));

        // Combine the adjusted win chance with the type effectiveness multiplier
        double winChance = powerAdjustedWinChance * typeEffectivenessMultiplier;

        return Math.min(winChance, 1.0); // Ensure it doesn't exceed 100%
    }
    /**
     * Calculates the base catch chance of an enemy Pokémon based solely on its stats. (normal ball)
     *
     * @param enemyPokemon The enemy Pokémon.
     * @return The base catch chance as a decimal (e.g., 0.3 for a 30% chance).
     */
    private double calculateBaseCatchChance(Pokemon enemyPokemon) {
        double FIXED_BIAS = 0.15;
        double result = (double) enemyPokemon.getHealth() / 100;
        double healthFactor = result + FIXED_BIAS;
        // For this example, a Pokémon with half its health gives a base 50% catch rate.
        return 1 - healthFactor;
    }

    /**
     * Calculates the chance of catching an enemy Pokémon,
     * taking into account the player's inventory and a specific Pokéball's effect.
     *
     * @param enemyPokemon The enemy Pokémon.
     * @param inventory The player's inventory.
     * @param pokeballType The specific Pokéball from the inventory to use.
     * @param requiredQuantity The quantity of the Pokéball required.
     * @return The catch chance as a decimal (e.g., 0.65 for a 65% chance).
     * @throws IllegalArgumentException If the player doesn't have enough of the specified Pokéball.
     */
    public double calculateCatchChance(Pokemon enemyPokemon, Inventory inventory, PokeBallType pokeballType, int requiredQuantity) {
        PokeBall tempBall = new PokeBall("", requiredQuantity, pokeballType);
        // Ensure the player has the required quantity of the Pokéball in their inventory
        if (inventory.getQuantity(tempBall) < requiredQuantity) {
            throw new IllegalArgumentException("Player doesn't have enough of the specified Pokéball in their inventory!");
        }

        double baseCatchChance = calculateBaseCatchChance(enemyPokemon);

        double ballModifier = 1.5;  // Regular Pokéball as default
        try {
            switch (pokeballType) {
                case GREATBALL -> ballModifier = 3;
            }
        } catch (IllegalArgumentException e) {
            // Handle the case where a Pokéball's name doesn't match any known type.
            throw new IllegalArgumentException("Invalid Pokéball provided!");
        }

        double catchChance = baseCatchChance * ballModifier;

        return Math.min(Math.max(catchChance, 0.0), 1.0); // Ensure it doesn't exceed 100% or go below 0%
    }

    public boolean attemptCatch(Pokemon enemyPokemon, Inventory inventory, PokeBallType pokeballType) {
        // Calculate the chance of catching the Pokemon with the specified Pokeball.
        double catchChance = calculateCatchChance(enemyPokemon, inventory, pokeballType, 1);

        // Remove one Pokeball of the specified type from the player's inventory.
        PokeBall pokeballUsed = new PokeBall("", 1, pokeballType);
        boolean hasBall = inventory.removePokeBall(pokeballUsed);

        if (!hasBall) {
            throw new IllegalArgumentException("Player doesn't have the specified Pokeball in their inventory!");
        }

        // Simulate catching the Pokemon.
        double randomValue = Math.random();  // generates a value between 0.0 (inclusive) and 1.0 (exclusive)
        if (randomValue <= catchChance) {
            inventory.addPokemon(enemyPokemon);
            return true;  // Pokemon was caught!
        }

        return false;  // Pokemon escaped!
    }

}
