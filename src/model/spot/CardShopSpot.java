package model.spot;

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

    }
}
