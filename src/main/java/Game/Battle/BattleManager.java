package Game.Battle;

import Game.GameLayout;
import GameObject.Item.BattleItem.BattleItem;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.Potion.Potion;
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
                attack();
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

    private void attack() throws IOException {
        // Assuming 'playerPokemon' is your player's current Pokémon and 'enemyPokemon' is the current opponent
        double winChance = battleCalculator.calculateWinChance(pokemonInfield, enemyPokemon);

        double randomValue = Math.random();  // Generates a value between 0.0 (inclusive) and 1.0 (exclusive)
        clearBattleTextBox();
        if (randomValue <= winChance) {
            // Player's Pokémon wins
            updateBattleTextBox(pokemonInfield.getName() + " defeated " + enemyPokemon.getName() + "!");
            System.out.println(pokemonInfield.getHealth());
            pokemonInfield.setHealth((int) (pokemonInfield.getHealth() * 0.8)); // lose 20% of its health per battle regardless
            System.out.println(pokemonInfield.getHealth());
        } else {
            // Enemy Pokémon wins
            updateBattleTextBox(enemyPokemon.getName() + " defeated " + pokemonInfield.getName() + "!");
            // Handle player loss scenarios here (e.g., Pokémon faints, player returns to last checkpoint)
            // Remove the player's Pokémon from the inventory after it's defeated
            // Search for the index of pokemonInfield in the player's inventory and remove it
            int index = player.getInventory().getIndexOfPokemon(pokemonInfield);
            if (index != -1) { // assuming -1 means not found
                player.getInventory().removePokemon(index);
            }
        }
        waitForUserInput();
        clearBattleTextBox();
        isBattleOver = true;
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


    private void usePotion(InventoryItem selectedPotion) throws IOException {
        if (selectedPotion instanceof Potion) { // Ensure the item is a potion
            Potion potion = (Potion) selectedPotion;

            // Heal the pokemonInfield
            pokemonInfield.heal(potion.getHealAmount());

            // Update the quantity of the potion in the player's inventory
            potion.setQuantity(potion.getQuantity() - 1);
            if (potion.getQuantity() <= 0) {
                // Remove the potion from the inventory if there's none left
                player.getInventory().removeItem(potion);
            }
            GameLayout.displayInventory();
            clearBattleTextBox();
            // Provide feedback to the player
            updateBattleTextBox(pokemonInfield.getName() + " was healed by " + potion.getHealAmount() + " points!");
            waitForUserInput();
        } else {
            clearBattleTextBox();
            updateBattleTextBox("This item can't be used as a potion!");
            waitForUserInput();
        }
    }


    private void displayBattleItemSelectGUI() throws IOException {
        List<InventoryItem> battleItems = player.getInventory().getBattleItems();
        StringBuilder sb = new StringBuilder();
        sb.append("Choose a battle item to use:");
        for (int i = 0; i < battleItems.size(); i++) {
            sb.append("\n\n").append(i).append(". ").append(battleItems.get(i).toString());
        }
        updateBattleTextBox(sb.toString());

        int index = getNumericInput(battleItems.size());
        if (index != -1) {
            InventoryItem selectedBattleItem = player.getInventory().getBattleItems().get(index);
            useBattleItem(selectedBattleItem);
        }
        clearBattleTextBox();
    }

    /**
     * Uses a BattleItem on the default Pokémon, enhancing its stats.
     *
     * @param selectedBattleItem the BattleItem to use.
     */
    private void useBattleItem(InventoryItem selectedBattleItem) throws IOException {
        if (selectedBattleItem instanceof BattleItem) {
            BattleItem battleItem = (BattleItem) selectedBattleItem;

            if (pokemonInfield != null) {
                int boostAmount = battleItem.getBoostAmount();
                String message = "";

                switch (battleItem.getType()) {
                    case XAttack:
                        pokemonInfield.setAttack(pokemonInfield.getAttack() + boostAmount);
                        message = pokemonInfield.getName() + "'s attack increased by " + boostAmount + "!";
                        break;
                    case XDefense:
                        pokemonInfield.setDefense(pokemonInfield.getDefense() + boostAmount);
                        message = pokemonInfield.getName() + "'s defense increased by " + boostAmount + "!";
                        break;
                    case XSpecialAttack:
                        pokemonInfield.setAttack(pokemonInfield.getAttack() + (2 * boostAmount));
                        message = pokemonInfield.getName() + "'s attack increased by " + (2 * boostAmount) + "!";
                        break;
                    case XSpecialDefence:
                        pokemonInfield.setDefense(pokemonInfield.getDefense() + (2 * boostAmount));
                        message = pokemonInfield.getName() + "'s defense increased by " + (2 * boostAmount) + "!";
                        break;
                    case XSpeed:
                        pokemonInfield.setAttack((int) (pokemonInfield.getAttack() + (0.5 * boostAmount)));
                        pokemonInfield.setDefense((int) (pokemonInfield.getDefense() + (0.5 * boostAmount)));
                        message = pokemonInfield.getName() + "'s attack and defense both increased by half the boost amount!";
                        break;
                    default:
                        message = "This BattleItem type is not recognized.";
                        break;
                }
                // Decrement the item's quantity and remove if necessary
                player.getInventory().useInventoryItem(battleItem);
                GameLayout.displayInventory();
                clearBattleTextBox();
                updateBattleTextBox(message);
                waitForUserInput();
                // Wait for a bit (if needed, you can use Thread.sleep() or any other mechanism)
                clearBattleTextBox();

            } else {
                updateBattleTextBox("No Pokémon available to use the item on.");
                clearBattleTextBox();
            }
        } else {
            updateBattleTextBox("The selected item is not a BattleItem.");
            clearBattleTextBox();
        }
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
        GameLayout.displayInventory();
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
