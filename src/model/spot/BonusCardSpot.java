package model.spot;

import action.event.BonusCardEvent;
import action.event.EventType;
import action.event.SimpleEventFactory;
import model.Player;

/**
 * Created by Ethan on 16/4/28.
 */
public class BonusCardSpot extends Spot {
    public BonusCardSpot() {
        this.typeName = "赠送道具点";
        this.description = "随机得到一个道具";
        this.spotType = SpotType.BonusCardSpot;
    }

    @Override
    public void arriveEvent(Player player) {
        BonusCardEvent event = (BonusCardEvent) SimpleEventFactory.createEvent(EventType.BONUS_CARD_EVENT);
        event.toggle(player);
    }

}
