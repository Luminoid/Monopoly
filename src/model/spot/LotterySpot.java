package model.spot;

/**
 * Created by Ethan on 16/4/29.
 */
public class LotterySpot extends Spot {
    @Override
    public SpotType getSpotType() {
        return SpotType.LotterySpot;
    }
}
