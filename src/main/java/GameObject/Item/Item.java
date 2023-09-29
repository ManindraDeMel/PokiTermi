package GameObject.Item;

public class Item {
    private String name;
    private String description;

    /**
     * Default constructor
     */
    public Item() {
    }

    /**
     * Constructor with name and description for the item.
     *
     * @param name        Name of the item.
     * @param description Description of the item.
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Use the item. This method should be overridden by subclasses to provide specific functionality.
     *
     * @return Message indicating the effect or use of the item.
     */
    public String use() {
        return "You used " + name + ".";
    }

    /**
     * Display the item. This can be further customized or overridden by subclasses.
     */
    public void display() {
        System.out.println(this);
    }

    /**
     * Dispose of the item. This method can be overridden by subclasses if disposal has special effects.
     */
    public void dispose() {
        System.out.println("You disposed of the " + name + ".");
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }

    // Getter and Setter methods remain unchanged.
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
