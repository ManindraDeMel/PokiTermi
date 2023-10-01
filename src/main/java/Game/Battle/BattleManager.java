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
import java.util.List;

import static Game.GameLayout.clearBattleTextBox;
import static Game.GameLayout.updateBattleTextBox;

public class BattleManager {

    private Player player;
    private Pokemon enemyPokemon;
    private BattleCalculations battleCalculator = new BattleCalculations();
    private ArrayList<InventoryItem> itemsUsed;
    private PokeBall defaultBall;
    private Pokemon pokemonInfield;
    public BattleManager(Player player, Pokemon enemyPokemon) {
        this.player = player;
        this.enemyPokemon = enemyPokemon;
        itemsUsed = new ArrayList<>();
        defaultBall = player.getInventory().getDefaultBall();
        pokemonInfield = player.getInventory().getDefaultPokemon();
    }

    public void startBattle() throws IOException {
        while (true) {
            displayBattleState();
            StringBuilder optionsText = new StringBuilder("\n\n\nChoose an option");

            // Check if the player has more than one Pokemon:
            if (player.getInventory().getPokemons().size() > 1) {
                optionsText.append("\n0. switch pokemon");
            }

            optionsText.append("\n1. attack");

            // Check for availability of balls, battle items, and potions:
            if (defaultBall != null) {
                optionsText.append("\n2. catch pokemon");
            }

            if (!player.getInventory().getBattleItems().isEmpty()) {
                optionsText.append("\n3. Use Battle Item");
            }

            if (!player.getInventory().getPotions().isEmpty()) {
                optionsText.append("\n4. Use Potion");
            }

            updateBattleTextBox(optionsText.toString());

            KeyStroke keyStroke = GameLayout.getTerminal().readInput();

            if (keyStroke.getKeyType() == KeyType.Character) {
                char ch = keyStroke.getCharacter();
                handleBattleOptions(ch);
            }
        }
    }

    private void handleBattleOptions(char option) throws IOException {
        switch (option) {
            case '0':
                if (player.getInventory().getPokemons().size() > 1) {
                    clearBattleTextBox();
                    displayPokemonSwitchGUI();
                }
                break;
            case '1':
                clearBattleTextBox();
                showAttackGUI();
                break;
            case '2':
                clearBattleTextBox();
                if (defaultBall != null) {
                    ChoosePokeBallGui();
                }
                else if (!player.getInventory().getBattleItems().isEmpty()) {
                    displayBattleItemSelectGUI();
                }
                else if (!player.getInventory().getPotions().isEmpty()) {
                    displayPotionSelectGUI();
                }
                break;
            case '3':
                clearBattleTextBox();
                if (defaultBall != null && !player.getInventory().getBattleItems().isEmpty()) {
                    displayBattleItemSelectGUI();
                }
                else if (!player.getInventory().getPotions().isEmpty()) {
                    displayPotionSelectGUI();
                }
                break;
            case '4':
                clearBattleTextBox();
                if (defaultBall != null && !player.getInventory().getBattleItems().isEmpty() && !player.getInventory().getPotions().isEmpty()) {
                    displayPotionSelectGUI();
                }
                break;
        }
    }


    private void showAttackGUI() {
    }

    private void displayPokemonSwitchGUI() throws IOException {
        // Assuming the player has a method getPokemons that returns a list of their Pokemon.
        List<Pokemon> pokemons = player.getInventory().getPokemons();
        updateBattleTextBox("Choose a Pokemon to switch:");
        for (int i = 0; i < pokemons.size(); i++) {
            updateBattleTextBox("\n\n" + i + ". " + pokemons.get(i).getName());
        }
        int index = getNumericInput(pokemons.size());
        if (index != -1) {
//            player.switchPokemon(index); // Assuming this method switches the active Pokemon to the chosen one.
        }
    }

    private void displayPotionSelectGUI() throws IOException {
        List<InventoryItem> potions = player.getInventory().getPotions();
        updateBattleTextBox("Choose a potion to use:");
        for (int i = 0; i < potions.size(); i++) {
            updateBattleTextBox("\n\n" + i + ". " + potions.get(i).getName());
        }
        int index = getNumericInput(potions.size());
        if (index != -1) {
//            player.usePotion(index); // Assuming a method for the player to use a potion.
        }
    }

    private void displayBattleItemSelectGUI() throws IOException {
        List<InventoryItem> battleItems = player.getInventory().getBattleItems();
        updateBattleTextBox("Choose a battle item to use:");
        for (int i = 0; i < battleItems.size(); i++) {
            updateBattleTextBox(i + ". " + battleItems.get(i).getName());
        }
        int index = getNumericInput(battleItems.size());
        if (index != -1) {
//            player.useBattleItem(index); // Assuming a method for the player to use a battle item.
        }
    }

    private void ChoosePokeBallGui() throws IOException {
        List<InventoryItem> balls = player.getInventory().getPokeBalls();
        updateBattleTextBox("Choose a Pokeball to use:");
        for (int i = 0; i < balls.size(); i++) {
            updateBattleTextBox(i + ". " + balls.get(i).getName());
        }
        int index = getNumericInput(balls.size());
        if (index != -1) {
//            player.usePokeBall(index); // Assuming a method for the player to use a Pokeball.
        }
    }

    // This method assumes you can read a numeric input from the player and validates it against the max index.
    private int getNumericInput(int maxIndex) throws IOException {
        try {
            KeyStroke keyStroke = GameLayout.getTerminal().readInput();
            if (keyStroke.getKeyType() == KeyType.Character) {
                int choice = Character.getNumericValue(keyStroke.getCharacter());
                if (choice >= 0 && choice < maxIndex) {
                    return choice;
                }
            }
        } catch (Exception e) {
            updateBattleTextBox("Invalid input. Please try again.");
        }
        return -1; // indicates an invalid input
    }

    private void displayBattleState() throws IOException {
        // For simplicity, just display chances to win/catch for now
        if (pokemonInfield != null) {
            double winChance = battleCalculator.calculateWinChance(pokemonInfield, enemyPokemon);
            updateBattleTextBox("Chance to win fight: " + String.format("%.2f", (winChance * 100)) + "%");

        }
        if (defaultBall != null) {
            int defaultPokeBallQty = 1;
            double catchChance = battleCalculator.calculateCatchChance(enemyPokemon, player.getInventory(), defaultBall.getType(), defaultPokeBallQty);
            updateBattleTextBox("\nChance to catch: " + String.format("%.2f", (catchChance * 100)) + "%\n");
        }
    }
}
