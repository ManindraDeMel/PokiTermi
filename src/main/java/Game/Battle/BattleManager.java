package Game.Battle;

import Game.GameLayout;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Player.Inventory.Inventory;
import GameObject.Player.Inventory.InventoryItem;
import GameObject.Pokemon.Pokemon;
import GameObject.Player.Player; // Assuming you have a Player class that contains inventory and list of Pokémon.
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Game.GameLayout.updateBattleTextBox;

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

    public void startBattle() throws IOException {
        while (true) {
            displayBattleState();
            if (defaultBall != null) {
                updateBattleTextBox("\n\n\n Choose an option \n 0. switch pokemon \n 1. attack \n 2. catch pokemon \n 3. Use Battle Item \n 4. Use Potion");
            } else {
                updateBattleTextBox("\n\n\n Choose an option \n 0. switch pokemon \n 1. attack \n 2. Use Battle Item \n 3. Use Potion");
            }

            KeyStroke keyStroke = GameLayout.getTerminal().readInput();

            if (keyStroke.getKeyType() == KeyType.Character) {
                char ch = keyStroke.getCharacter();
                System.out.println(ch);
                handleBattleOptions(ch);
            }
        }
    }

    private void handleBattleOptions(char option) throws IOException {
        switch (option) {
            case '0':
                displayPokemonSwitchGUI();
                break;
            case '1':
                performAttack();
                if (battleEnded()) {
                    displayResult();
                    return;
                }
                break;
            case '2':
                if (defaultBall != null) {
                    ChoosePokeBallGui();
                } else {
                    displayBattleItemSelectGUI();
                }
                break;
            case '3':
                if (defaultBall != null) {
                    displayBattleItemSelectGUI();
                } else {
                    displayPotionSelectGUI();
                }
                break;
            case '4':
                displayPotionSelectGUI();
                break;
            default:
                updateBattleTextBox("Incorrect input");
        }
    }

    private void displayPokemonSwitchGUI() {
        // Display GUI for switching Pokemon
    }

    private void displayPotionSelectGUI() {
        // Display GUI for selecting a potion
    }

    private void displayBattleItemSelectGUI() {
        // Display GUI for selecting a battle item
    }

    private void ChoosePokeBallGui() {
        // Display GUI for choosing a Pokeball
    }

    private void usePotion() {
    }

    private void displayBattleState() throws IOException {
        // For simplicity, just display chances to win/catch for now
        if (defaultPokemon != null) {
            double winChance = battleCalculator.calculateWinChance(defaultPokemon, enemyPokemon);
            updateBattleTextBox("Chance to win fight: " + String.format("%.2f", (winChance * 100)) + "%");

        }
        if (defaultBall != null) {
            int defaultPokeBallQty = 1;
            double catchChance = battleCalculator.calculateCatchChance(enemyPokemon, player.getInventory(), defaultBall.getType(), defaultPokeBallQty);
            updateBattleTextBox("\n\nChance to catch: " + (catchChance * 100) + "%");
        }
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

    private void useBattleItem() {
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
