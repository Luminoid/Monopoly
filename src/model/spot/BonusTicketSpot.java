package model.spot;

import action.event.BonusTicketEvent;
import action.event.EventType;
import action.event.SimpleEventFactory;
import model.Player;

/**
 * Created by Ethan on 16/4/28.
 */
public class BonusTicketSpot extends Spot {
    public BonusTicketSpot() {
        this.typeName = "赠送点券点";
        this.description = "随机获得一定数量的点券";
        this.spotType = SpotType.BonusTicketSpot;
    }

    @Override
    public void arriveEvent(Player player) {
        BonusTicketEvent event = (BonusTicketEvent) SimpleEventFactory.createEvent(EventType.BONUS_TICKET_EVENT);
        event.toggle(player);
    }
}
