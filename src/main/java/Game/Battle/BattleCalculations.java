package Game.Battle;

import GameObject.Item.BattleItem.BattleItemType;
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
     * Calculates the chance of the player's Pokémon winning against an enemy Pokémon,
     * taking into account the player's inventory and a specific item's effect.
     *
     * @param playerPokemon The player's Pokémon.
     * @param enemyPokemon The enemy Pokémon.
     * @param inventory The player's inventory.
     * @param item The specific item from the inventory to account for.
     * @param requiredQuantity The quantity of the item required for its effect.
     * @return The win chance as a decimal (e.g., 0.85 for an 85% chance).
     * @throws IllegalArgumentException If the player doesn't have enough of the specified item.
     */
    public double calculateWinChance(Pokemon playerPokemon, Pokemon enemyPokemon, Inventory inventory, InventoryItem item, int requiredQuantity) {
        // Ensure the player has the required quantity of the item in their inventory
        if (inventory.getQuantity(item) < requiredQuantity) {
            throw new IllegalArgumentException("Player doesn't have enough of the specified item in their inventory!");
        }

        double winChance = calculateWinChance(playerPokemon, enemyPokemon);  // Calculate base win chance

        double playerAttackMultiplier = 1.0;
        double playerDefenseMultiplier = 1.0;
        double playerEffectiveHealth = playerPokemon.getHealth();

        if (item instanceof Potion) {
            playerEffectiveHealth += ((Potion) item).getHealAmount() * requiredQuantity;
        } else {
            try {
                switch (BattleItemType.valueOf(item.getName())) {
                    case XAttack, XSpecialAttack, XDefense, XSpecialDefence, XSpeed:
                        playerAttackMultiplier += 0.10 * requiredQuantity;  // Increase attack by 10% for each used item
                        break;
                }
            } catch (IllegalArgumentException e) {
                // Handle the case where an item's name doesn't match any BattleItemType.
                throw new IllegalArgumentException("Invalid battle item provided!");
            }
        }

        // Factor in the enhanced stats into win chance calculation
        double playerEffectiveAttack = playerPokemon.getAttackValue() * playerAttackMultiplier;
        double playerEffectiveDefense = playerPokemon.getDefenseValue() * playerDefenseMultiplier;

        double statEffect = (playerEffectiveAttack / enemyPokemon.getAttackValue()) *
                (playerEffectiveDefense / enemyPokemon.getDefenseValue()) *
                (playerEffectiveHealth / enemyPokemon.getHealth());

        winChance *= statEffect;

        return Math.min(Math.max(winChance, 0.0), 1.0); // Ensure it doesn't exceed 100% or go below 0%
    }
    /**
     * Calculates the base catch chance of an enemy Pokémon based solely on its stats. (normal ball)
     *
     * @param enemyPokemon The enemy Pokémon.
     * @return The base catch chance as a decimal (e.g., 0.3 for a 30% chance).
     */
    private double calculateBaseCatchChance(Pokemon enemyPokemon) {
        double FIXED_BIAS = 0.15;
        double healthFactor = (enemyPokemon.getHealth() / 100) + FIXED_BIAS;
        // For this example, a Pokémon with half its health gives a base 50% catch rate.
        return 1 - healthFactor;
    }

    /**
     * Calculates the chance of catching an enemy Pokémon,
     * taking into account the player's inventory and a specific Pokéball's effect.
     *
     * @param enemyPokemon The enemy Pokémon.
     * @param inventory The player's inventory.
     * @param pokeBall The specific Pokéball from the inventory to use.
     * @param requiredQuantity The quantity of the Pokéball required.
     * @return The catch chance as a decimal (e.g., 0.65 for a 65% chance).
     * @throws IllegalArgumentException If the player doesn't have enough of the specified Pokéball.
     */
    public double calculateCatchChance(Pokemon enemyPokemon, Inventory inventory, InventoryItem pokeBall, int requiredQuantity) {
        // Ensure the player has the required quantity of the Pokéball in their inventory
        if (inventory.getQuantity(pokeBall) < requiredQuantity) {
            throw new IllegalArgumentException("Player doesn't have enough of the specified Pokéball in their inventory!");
        }

        double baseCatchChance = calculateBaseCatchChance(enemyPokemon);

        double ballModifier = 1.0;  // Regular Pokéball as default
        try {
            switch (PokeBallType.valueOf(pokeBall.getName())) {
                case GREATBALL:
                    ballModifier = 2;
                    break;
            }
        } catch (IllegalArgumentException e) {
            // Handle the case where a Pokéball's name doesn't match any known type.
            throw new IllegalArgumentException("Invalid Pokéball provided!");
        }

        double catchChance = baseCatchChance * ballModifier;

        return Math.min(Math.max(catchChance, 0.0), 1.0); // Ensure it doesn't exceed 100% or go below 0%
    }

}
