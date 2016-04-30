package model.spot;

/**
 * Created by Ethan on 16/4/28.
 */
public class BonusTicketSpot extends Spot {
    @Override
    public void arriveEvent() {

    }

    @Override
    public SpotType getSpotType() {
        return SpotType.BonusTicketSpot;
    }

    @Override
    public String getTypeName() {
        return "赠送点券点";
    }

    @Override
    public String getSpotDescription() {
        return "随机获得一定数量的点券";
    }
}
