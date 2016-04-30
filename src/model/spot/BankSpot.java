package model.spot;

import action.event.BankEvent;
import action.event.EventType;
import action.event.SimpleEventFactory;
import model.Player;

/**
 * Created by Ethan on 16/4/28.
 */
public class BankSpot extends Spot {
    public BankSpot() {
        this.typeName = "银行";
        this.description = "存取钱";
        this.spotType = SpotType.BankSpot;
    }

    @Override
    public void arriveEvent(Player player) {
        BankEvent event = (BankEvent) SimpleEventFactory.createEvent(EventType.BANK_EVENT);
        event.toggle(player);
    }

    @Override
    public void passByEvent(Player player) {
        arriveEvent(player);
    }
}
