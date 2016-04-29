package model.spot;

/**
 * Created by Ethan on 16/4/27.
 */
public abstract class Spot {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract SpotType getSpotType();
}
