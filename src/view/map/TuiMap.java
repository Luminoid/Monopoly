package view.map;

import model.Map;
import model.spot.Spot;

import java.util.Hashtable;

/**
 * Created by Ethan on 16/4/29.
 */
public class TuiMap extends Map {
    private int width;
    private int height;
    public Hashtable<Spot, SpotLoc> spotLoc;

    public TuiMap() {
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
