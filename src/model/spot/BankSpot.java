package model.spot;

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

    }
}
