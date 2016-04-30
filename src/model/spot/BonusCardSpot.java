package model.spot;

/**
 * Created by Ethan on 16/4/28.
 */
public class BonusCardSpot extends Spot {
    @Override
    public void arriveEvent() {

    }

    @Override
    public SpotType getSpotType() {
        return SpotType.BonusCardSpot;
    }

    @Override
    public String getTypeName() {
        return "赠送道具点";
    }

    @Override
    public String getSpotDescription() {
        return "随机得到一个道具";
    }
}
