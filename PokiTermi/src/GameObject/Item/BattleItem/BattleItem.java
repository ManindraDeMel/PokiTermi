package GameObject.Item.BattleItem;

import GameObject.Item.Item;

public class BattleItem extends Item {
    public int boostAmount;
    public int num;
    public BattleItemType type;

    public BattleItem(int num, BattleItemType type) {
        super();
        super.setName("BattleItem");
        this.boostAmount = 20; // Boosts Special Attack/Defence by 20.
        this.num = num;
        this.type = type;
    }
}