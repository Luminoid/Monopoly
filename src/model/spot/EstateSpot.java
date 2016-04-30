package model.spot;

import model.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ethan on 16/4/28.
 */
public class EstateSpot extends Spot {
    private final int MAX_LEVEL = 6;
    private Player owner;
    private double basePrice;
    private int level;

    @Override
    public void arriveEvent() {

    }

    @Override
    public SpotType getSpotType() {
        return SpotType.EstateSpot;
    }

    @Override
    public String getTypeName() {
        return "土地";
    }

    @Override
    public String getSpotDescription() {
        return owner == null ? "可供出售的土地" : "已被购买的土地";
    }

    public List<String> getSpotInfo() {
        List<String> spotInfo = new LinkedList<>();
        spotInfo.add("地名：" + this.getLocName());
        spotInfo.add("类型：" + getTypeName());
        spotInfo.add("简介：" + getSpotDescription());
        return spotInfo;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
