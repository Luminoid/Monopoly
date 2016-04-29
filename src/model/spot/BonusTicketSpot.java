package model.spot;

/**
 * Created by Ethan on 16/4/28.
 */
public class BonusTicketSpot extends Spot {
    @Override
    public SpotType getSpotType() {
        return SpotType.BonusTicketSpot;
    }
}
