package model.spot;

import model.Player;

/**
 * Created by Ethan on 16/4/28.
 */
public class EmptySpot extends Spot {
    public EmptySpot() {
        this.typeName = "空地";
        this.description = "无事发生";
        this.spotType = SpotType.EmptySpot;
    }

    @Override
    public void arriveEvent(Player player) {
        // Nothing
    }
}
