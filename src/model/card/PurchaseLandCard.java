package model.card;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.Player;
import model.spot.EstateSpot;
import util.PlayerUtil;

/**
 * Created by Ethan on 16/4/27.
 */
public class PurchaseLandCard extends Card {
    public PurchaseLandCard() {
        this.name = "购地卡";
        this.description = "强行用现价购买自己当前所在位置的土地,当然发动者不能购买自己的房屋";
        this.value = 15;
        this.cardType = CardType.PURCHASE_LAND_CARD;
    }

    @Override
    public boolean use(Player player) {
        EstateSpot spot = (EstateSpot) PlayerUtil.getPlayerSpot(player);
        if (spot.getOwner() != player && player.getCash() >= spot.getPurchasingPrice()) {
            player.pay(spot.getPurchasingPrice());
            ((EstateSpot) PlayerUtil.getPlayerSpot(player)).getOwner().addCash(spot.getPurchasingPrice());
            ((EstateSpot) PlayerUtil.getPlayerSpot(player)).getOwner().getHouses().remove(spot);
            player.getHouses().add(spot);
            spot.setOwner(player);
            PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
            command.setCommandStr(player.getName() + " 花费¥" + spot.getPurchasingPrice() + "买下了" + spot.getLocName() + "的房子");
            return true;
        } else {
            return false;
        }
    }
}
