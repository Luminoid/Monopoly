package model.spot;

import model.Player;

/**
 * Created by Ethan on 16/6/20.
 */
public class HospitalSpot extends Spot {
    public HospitalSpot() {
        this.typeName = "医院";
        this.description = "受伤的玩家会被送到这里";
        this.spotType = SpotType.HospitalSpot;
    }

    @Override
    public void arriveEvent(Player player) {
        // Nothing
    }
}
