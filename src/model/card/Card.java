package model.card;

/**
 * Created by Ethan on 16/4/27.
 */
public abstract class Card {
    protected String type;
    protected String name;
    protected String description;

    public abstract boolean use();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
