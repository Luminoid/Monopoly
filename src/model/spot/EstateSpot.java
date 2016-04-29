package model.spot;

import model.Player;

/**
 * Created by Ethan on 16/4/28.
 */
public class EstateSpot extends Spot {
    private Player owner;

    @Override
    public SpotType getSpotType() {
        return SpotType.EstateSpot;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
