package model.spot;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ethan on 16/4/27.
 */
public abstract class Spot {
    private String locName;
    private String typeName;

    public abstract void arriveEvent();

    public abstract SpotType getSpotType();

    public abstract String getTypeName();

    public abstract String getSpotDescription();

    public List<String> getSpotInfo() {
        List<String> spotInfo = new LinkedList<>();
        spotInfo.add("地名：" + locName);
        spotInfo.add("类型：" + getTypeName());
        spotInfo.add("简介：" + getSpotDescription());
        return spotInfo;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }
}
