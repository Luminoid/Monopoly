package model.map;

import model.spot.Spot;

import java.util.Hashtable;

/**
 * Created by Ethan on 16/4/29.
 */
public class RegMap extends Map {
    private int width;
    private int height;
    public Hashtable<Spot, SpotLoc> spotLoc;

    public RegMap() {
        super();
        spotLoc = new Hashtable<>();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
