package model.spot;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ethan on 16/4/28.
 */
public class EmptySpot extends Spot {
    @Override
    public void arriveEvent() {

    }

    @Override
    public SpotType getSpotType() {
        return SpotType.EmptySpot;
    }

    @Override
    public String getTypeName() {
        return "空地";
    }

    @Override
    public String getSpotDescription() {
        return "";
    }

    public List<String> getSpotInfo() {
        List<String> spotInfo = new LinkedList<>();
        spotInfo.add("地名：" + this.getLocName());
        spotInfo.add("类型：" + getTypeName());
        return spotInfo;
    }
}
