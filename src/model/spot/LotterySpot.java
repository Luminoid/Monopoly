package model.spot;

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

    }

}
