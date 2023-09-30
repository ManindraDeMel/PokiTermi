package Game.Battle;

import GameObject.Item.PokeBall.PokeBall;
import GameObject.Player.Inventory.Inventory;
import GameObject.Player.Inventory.InventoryItem;
import GameObject.Pokemon.Pokemon;
import GameObject.Player.Player; // Assuming you have a Player class that contains inventory and list of Pokémon.

import java.util.ArrayList;
import java.util.Scanner;

public class BattleManager {

    private Player player;
    private Pokemon enemyPokemon;
    private BattleCalculations battleCalculator = new BattleCalculations();
    private ArrayList<InventoryItem> itemsUsed;
    private PokeBall defaultBall;
    private Pokemon defaultPokemon;
    public BattleManager(Player player, Pokemon enemyPokemon) {
        this.player = player;
        this.enemyPokemon = enemyPokemon;
        itemsUsed = new ArrayList<>();
        defaultBall = player.getInventory().getDefaultBall();
        defaultPokemon = player.getInventory().getDefaultPokemon();
    }

    public void startBattle() {
        while (true) {
            displayBattleState();
            int option = getPlayerInput();

            switch (option) {
                case 0:
                    switchPokemon();
                    break;
                case 1:
                    performAttack();
                    if (battleEnded()) {
                        displayResult();
                        return;
                    }
                    break;
                case 2:
                    if (attemptCatch()) {
                        displayResult();
                        return;
                    }
                    break;
                case 3:
                    useItem();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displayBattleState() {
        // For simplicity, just display chances to win/catch for now
        double winChance = battleCalculator.calculateWinChance(defaultPokemon, enemyPokemon);
        System.out.println("Chance to win: " + (winChance * 100) + "%");
        int defaultPokeBallQty = 1;
        double catchChance = battleCalculator.calculateCatchChance(enemyPokemon, player.getInventory(), defaultBall.getType(), defaultPokeBallQty);
        System.out.println("Chance to catch: " + (catchChance * 100) + "%");
    }

    private int getPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an action:");
        System.out.println("0: Switch Pokemon");
        System.out.println("1: Attack");
        System.out.println("2: Catch Pokemon");
        System.out.println("3: Use Item");
        return scanner.nextInt();
    }

    private void switchPokemon() {
        System.out.println("Choose a Pokemon:");
        // Display available Pokémon and let the player choose one by index
        // Set player's current Pokémon accordingly
    }

    private void performAttack() {
        // Simplified logic
        double outcome = Math.random(); // Random outcome
        double winChance = battleCalculator.calculateWinChance(defaultPokemon, enemyPokemon);
        if (outcome <= winChance) {
            System.out.println("You attacked successfully!");
            enemyPokemon.setHealth(enemyPokemon.getHealth() - 20); // Sample damage logic
        } else {
            System.out.println("Attack failed!");
            defaultPokemon.setHealth(defaultPokemon.getHealth() - 20); // Sample damage logic
        }
    }

    private boolean battleEnded() {
        return defaultPokemon.getHealth() <= 0 || enemyPokemon.getHealth() <= 0;
    }

    private boolean attemptCatch() {
        // Let the player choose which ball to use
        // Calculate the catch chance and determine if the Pokémon is caught
        // Return true if caught, false otherwise
        return false;
    }

    private void useItem() {
        // Show available items and let the player choose one by index
        // Apply the item's effect and update stats/chances
    }

    private void displayResult() {
        if (enemyPokemon.getHealth() <= 0) {
            System.out.println("You won the battle!");
        } else if (defaultPokemon.getHealth() <= 0) {
            System.out.println("You lost the battle!");
        } else {
            System.out.println("You caught the Pokémon!");
        }
    }
}
