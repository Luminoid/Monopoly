package model;

import model.spot.Spot;

import java.util.ArrayList;

/**
 * Created by Ethan on 16/4/27.
 */
public abstract class Map {
    private ArrayList<Spot> spotList;
    private Spot StartSpot;

    public Map() {
        spotList = new ArrayList<>();
    }

    public void addSpot(Spot spot) {
        spotList.add(spot);
    }

    public ArrayList<Spot> getSpots() {
        return spotList;
    }

    public int getSize() {
        return spotList.size();
    }
}
