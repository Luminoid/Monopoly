package model.spot;

import action.event.CardShopEvent;
import action.event.EventType;
import action.event.SimpleEventFactory;
import model.Player;

/**
 * Created by Ethan on 16/4/28.
 */
public class CardShopSpot extends Spot {
    public CardShopSpot() {
        this.typeName = "道具店";
        this.description = "可以使用点券在道具店购买道具";
        this.spotType = SpotType.CardShopSpot;
    }

    @Override
    public void arriveEvent(Player player) {
        CardShopEvent event = (CardShopEvent) SimpleEventFactory.createEvent(EventType.CARD_SHOP_EVENT);
        event.toggle(player);
    }
}
