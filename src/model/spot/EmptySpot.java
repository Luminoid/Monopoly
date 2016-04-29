package model.spot;

/**
 * Created by Ethan on 16/4/28.
 */
public class EmptySpot extends Spot {
    @Override
    public SpotType getSpotType() {
        return SpotType.EmptySpot;
    }
}
