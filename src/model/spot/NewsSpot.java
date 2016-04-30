package model.spot;

/**
 * Created by Ethan on 16/4/28.
 */
public class NewsSpot extends Spot {
    @Override
    public void arriveEvent() {

    }

    @Override
    public SpotType getSpotType() {
        return SpotType.NewsSpot;
    }

    @Override
    public String getTypeName() {
        return "新闻";
    }

    @Override
    public String getSpotDescription() {
        return "随机播报一个新闻";
    }
}
