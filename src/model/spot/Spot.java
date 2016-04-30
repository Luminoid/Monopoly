package model.spot;

import model.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ethan on 16/4/27.
 */
public abstract class Spot {
    private String locName;
    protected String typeName;
    protected String description;
    protected SpotType spotType;
    protected boolean isBlocked;

    public abstract void arriveEvent(Player player);

    public void passByEvent(Player player) {
        // Bank Spot
    }

    public List<String> getSpotInfo() {
        List<String> spotInfo = new LinkedList<>();
        spotInfo.add("地名：" + locName);
        spotInfo.add("类型：" + typeName);
        spotInfo.add("简介：" + description);
        return spotInfo;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDescription() {
        return description;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
