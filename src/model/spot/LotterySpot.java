package model.spot;

import action.event.EventType;
import action.event.LotteryEvent;
import action.event.SimpleEventFactory;
import model.Player;

/**
 * Created by Ethan on 16/4/29.
 */
public class LotterySpot extends Spot {
    public LotterySpot() {
        this.typeName = "彩票点";
        this.description = "买彩票";
        this.spotType = SpotType.LotterySpot;
    }

    @Override
    public void arriveEvent(Player player) {
        LotteryEvent event = (LotteryEvent) SimpleEventFactory.createEvent(EventType.LOTTERY_EVENT);
        event.toggle(player);
    }
}
