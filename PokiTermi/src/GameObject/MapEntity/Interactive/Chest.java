package GameObject.MapEntity.Interactive;

import GameObject.Item.Item;
import GameObject.Item.PokeBall.GreatBall;
import GameObject.MapEntity.Coordinate.Coordinate;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.Random;

public class Chest extends Coordinate {

    public static ArrayList<Enum> itemList = new ArrayList<>() ;//finish this add Potion SuperPotion NormalBall GreatBall XAttack XDefense XSpecialAttack XSpecialDefence XSpeed

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
    public Chest(int row, int col) {
        super(row, col);
        setSymbol('?');
        setColor(TextColor.ANSI.YELLOW_BRIGHT);
    }

    @Override
    public void display() {
        super.display();

    }

    public Item open(){

        Random random = new Random();
        int randomIndex = random.nextInt(itemList.size());
        ItemType selectedItemType = (ItemType) itemList.get(randomIndex);

        // Create an instance of Item based on the selected ItemType
        Item foundItem = new GreatBall(); // Assuming you have a constructor in Item class that takes ItemType

        // Add information at the same position with lookAround text
        String message = "You opened the chest and found a/an " + selectedItemType.name() + "!";
        // Assuming you have a method to display messages in your game
        return foundItem;
    }
}
