package GameObject.Item.PokeBall;


import GameObject.Item.Item;

public class PokeBall extends Item {
    public int num;
    public PokeBallType type;

    public PokeBall(int num, PokeBallType type) {
        super();
        super.setName("PokeBall");
        this.num = num;
        this.type = type;
    }
}