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
    private PokeBall defaultBall;
    private Pokemon pokemonInfield;
    private boolean isBattleOver;
    public BattleManager(Player player, Pokemon enemyPokemon) {
        this.player = player;
        this.enemyPokemon = enemyPokemon;
        defaultBall = player.getInventory().getDefaultBall();
        pokemonInfield = player.getInventory().getDefaultPokemon();
        isBattleOver = false;
    }

    public void startBattle() throws IOException {
        while (true) {
            if (isBattleOver) {
                return;
            }
            displayBattleState();
            StringBuilder optionsText = new StringBuilder("\n\n\n\n\nChoose an option");

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
                    return;
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
        List<Pokemon> pokemons = player.getInventory().getPokemons();
        StringBuilder sb = new StringBuilder();
        sb.append("Choose a Pokemon to switch:");
        for (int i = 0; i < pokemons.size(); i++) {
            sb.append("\n\n").append(i).append(". ").append(pokemons.get(i).getName());
        }
        updateBattleTextBox(sb.toString());

        int index = getNumericInput(pokemons.size());
        if (index != -1) {
            pokemonInfield = player.getInventory().getPokemons().get(index);
        }
        clearBattleTextBox();
    }


    private void displayPotionSelectGUI() throws IOException {
        List<InventoryItem> potions = player.getInventory().getPotions();
        StringBuilder sb = new StringBuilder();
        sb.append("Choose a potion to use:");
        for (int i = 0; i < potions.size(); i++) {
            sb.append("\n\n").append(i).append(". ").append(potions.get(i).getName());
        }
        updateBattleTextBox(sb.toString());

        int index = getNumericInput(potions.size());
        if (index != -1) {
            InventoryItem selectedPotion = player.getInventory().getPotions().get(index);
            usePotion(selectedPotion);
        }
        clearBattleTextBox();
    }


    private void usePotion(InventoryItem selectedPotion) {
    }

    private void displayBattleItemSelectGUI() throws IOException {
        List<InventoryItem> battleItems = player.getInventory().getBattleItems();
        StringBuilder sb = new StringBuilder();
        sb.append("Choose a battle item to use:");
        for (int i = 0; i < battleItems.size(); i++) {
            sb.append("\n\n").append(i).append(". ").append(battleItems.get(i).getName());
        }
        updateBattleTextBox(sb.toString());

        int index = getNumericInput(battleItems.size());
        if (index != -1) {
            InventoryItem selectedBattleItem = player.getInventory().getBattleItems().get(index);
            useBattleItem(selectedBattleItem);
        }
        clearBattleTextBox();
    }

    private void useBattleItem(InventoryItem selectedBattleItem) {
    }

    private void ChoosePokeBallGui() throws IOException {
        List<InventoryItem> balls = player.getInventory().getPokeBalls();
        StringBuilder sb = new StringBuilder();
        sb.append("Choose a Pokeball to use:");
        for (int i = 0; i < balls.size(); i++) {
            sb.append("\n\n").append(i).append(". ").append(balls.get(i).getName());
        }
        updateBattleTextBox(sb.toString());

        int index = getNumericInput(balls.size());
        if (index != -1) {
            PokeBall selectedBall = (PokeBall) player.getInventory().getPokeBalls().get(index);
            defaultBall = selectedBall; // Set the selected ball as the default.
            usePokeball(defaultBall);
        }
    }


    private void usePokeball(PokeBall ballUsed) throws IOException {
        BattleCalculations battleCalc = new BattleCalculations();
        boolean caught = battleCalc.attemptCatch(enemyPokemon, player.getInventory(), ballUsed.getType());
        clearBattleTextBox();
        if (caught) {
            updateBattleTextBox("Successfully caught " + enemyPokemon.getName() + "!");
        } else {
            updateBattleTextBox(enemyPokemon.getName() + " escaped!");
        }
        waitForUserInput(); // Wait for any key press
        clearBattleTextBox();
        isBattleOver = true;
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
    private void waitForUserInput() throws IOException {
        GameLayout.getTerminal().readInput();  // Just wait for any key input from the user
    }

    private void displayBattleState() throws IOException {
        // For simplicity, just display chances to win/catch for now
        updateBattleTextBox(pokemonInfield + " Is on field!");
        if (pokemonInfield != null) {
            double winChance = battleCalculator.calculateWinChance(pokemonInfield, enemyPokemon);
            updateBattleTextBox("\n\nChance to win fight: " + String.format("%.2f", (winChance * 100)) + "%");

        }
        if (defaultBall != null && !player.getInventory().getPokeBalls().isEmpty()) {
            int defaultPokeBallQty = 1;
            double catchChance = battleCalculator.calculateCatchChance(enemyPokemon, player.getInventory(), defaultBall.getType(), defaultPokeBallQty);
            updateBattleTextBox("\nChance to catch: " + String.format("%.2f", (catchChance * 100)) + "%\n\n");
        }
    }
}
