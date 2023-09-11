package GameObject.Item;

public abstract class  Item {
    private String name;
    private String description;

    public Item() {
    }
    public abstract void use();


    public abstract void display();

    public void despose(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
