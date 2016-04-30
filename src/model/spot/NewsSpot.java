package model.spot;

import model.Player;

/**
 * Created by Ethan on 16/4/28.
 */
public class NewsSpot extends Spot {
    public NewsSpot() {
        this.typeName = "新闻";
        this.description = "随机播报一个新闻";
        this.spotType = SpotType.NewsSpot;
    }

    @Override
    public void arriveEvent(Player player) {

    }
}
