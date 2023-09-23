package GameObject.Item.PokeBall;

import GameObject.Item.Item;

public class NormalBall extends Item {

    private int catchRate;
    public NormalBall() {
        super();
        super.setName("Poké Ball");
        this.catchRate = 1;
    }
    @Override
    public void use() {

    }

    @Override
    public void display() {

    }
}
