package GameObject.MapEntity.Interactive;

import GameObject.Item.BattleItem.BattleItem;
import GameObject.Item.BattleItem.BattleItemType;
import GameObject.Item.Item;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.Item.Potion.Potion;
import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static Game.GameLayout.displayMessage;

/**
 * Represents a Chest in the game layout which can contain items that players can acquire.
 *
 * @author Yiming Lu
 */
public class Chest extends Coordinate {

    public static ArrayList<Enum> itemList = new ArrayList<>();

    public enum ItemType {
        Potion, SuperPotion, NormalBall, GreatBall, XAttack, XDefense, XSpecialAttack, XSpecialDefence, XSpeed
    }

    static {
        // Initializing the itemList
        itemList.add(ItemType.Potion);
        itemList.add(ItemType.SuperPotion);
        itemList.add(ItemType.NormalBall);
        itemList.add(ItemType.GreatBall);
        itemList.add(ItemType.XAttack);
        itemList.add(ItemType.XDefense);
        itemList.add(ItemType.XSpecialAttack);
        itemList.add(ItemType.XSpecialDefence);
        itemList.add(ItemType.XSpeed);
    }

    /**
     * Constructs a new Chest with the specified row and column positions.
     *
     * @param row the row position of the Chest.
     * @param col the column position of the Chest.
     */
    public Chest(int row, int col) {
        super(row, col);
        setSymbol('?');
        setColor(TextColor.ANSI.YELLOW_BRIGHT);
    }

    @Override
    public void display() {
        super.display();
    }

    /**
     * Opens the chest and provides a random item to the player.
     *
     * @return the Item that the player acquired from the chest.
     * @throws IOException if there's an issue with I/O operations.
     *
     * @author Manindra de Mel
     */
    public Item open() throws IOException {

        Random random = new Random();
        int randomIndex = random.nextInt(itemList.size());
        ItemType selectedItemType = (ItemType) itemList.get(randomIndex);

        Item foundItem;

        // Create an instance of Item based on the selected ItemType
        switch (selectedItemType) {
            case Potion:
                foundItem = new Potion(1);
                break;
            case SuperPotion:
                foundItem = new Potion(1, 100); // Assuming SuperPotion heals 100 HP
                break;
            case NormalBall:
                foundItem = new PokeBall("NormalBall", 1, PokeBallType.NORMALBALL);
                break;
            case GreatBall:
                foundItem = new PokeBall("GreatBall", 1, PokeBallType.GREATBALL);
                break;
            case XAttack:
                foundItem = new BattleItem(1, BattleItemType.XAttack);
                break;
            case XDefense:
                foundItem = new BattleItem(1, BattleItemType.XDefense);
                break;
            case XSpecialAttack:
                foundItem = new BattleItem(1, BattleItemType.XSpecialAttack);
                break;
            case XSpecialDefence:
                foundItem = new BattleItem(1, BattleItemType.XSpecialDefence);
                break;
            case XSpeed:
                foundItem = new BattleItem(1, BattleItemType.XSpeed);
                break;
            default:
                foundItem = new PokeBall("NormalBall", 1, PokeBallType.NORMALBALL);
                break;
        }

        String message = "You found an item";
        displayMessage(message);
        return foundItem;
    }
}
