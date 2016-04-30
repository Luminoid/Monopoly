package model.spot;

import action.event.BonusCardEvent;
import action.command.CommandType;
import action.command.SimpleCommamdFactory;
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
        BonusCardEvent event = (BonusCardEvent) SimpleCommamdFactory.createCommand(CommandType.BONUS_CARD_EVENT);
        event.toggle(player);
    }

}
