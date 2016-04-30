package model.spot;

/**
 * Created by Ethan on 16/4/28.
 */
public class CardShopSpot extends Spot {
    @Override
    public void arriveEvent() {

    }

    @Override
    public SpotType getSpotType() {
        return SpotType.CardShopSpot;
    }

    @Override
    public String getTypeName() {
        return "道具店";
    }

    @Override
    public String getSpotDescription() {
        return "可以使用点券在道具店购买道具";
    }
}
