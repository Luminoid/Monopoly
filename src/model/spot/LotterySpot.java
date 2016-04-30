package model.spot;

/**
 * Created by Ethan on 16/4/29.
 */
public class LotterySpot extends Spot {
    @Override
    public void arriveEvent() {

    }

    @Override
    public SpotType getSpotType() {
        return SpotType.LotterySpot;
    }

    @Override
    public String getTypeName() {
        return "彩票点";
    }

    @Override
    public String getSpotDescription() {
        return "买彩票";
    }
}
