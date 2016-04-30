package model.spot;

import action.event.EventType;
import action.event.NewsEvent;
import action.event.SimpleEventFactory;
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
        NewsEvent event = (NewsEvent) SimpleEventFactory.createEvent(EventType.NEWS_EVENT);
        event.toggle(player);
    }
}
