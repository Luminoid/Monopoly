package model.spot;

/**
 * Created by Ethan on 16/4/28.
 */
public class BankSpot extends Spot {
    @Override
    public void arriveEvent() {

    }

    @Override
    public SpotType getSpotType() {
        return SpotType.BankSpot;
    }

    @Override
    public String getTypeName() {
        return "银行";
    }

    @Override
    public String getSpotDescription() {
        return "存取钱";
    }

}
