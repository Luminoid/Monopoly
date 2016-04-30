package model.card;

import model.Player;
import model.spot.SpotType;

/**
 * Created by Ethan on 16/4/27.
 */
public abstract class Card {
    protected String name;
    protected String description;
    protected SpotType spotType;
    protected int value;

    public abstract boolean use(Player player);

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
