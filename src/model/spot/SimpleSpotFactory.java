package model.spot;

import java.util.Random;

/**
 * Created by Ethan on 16/4/28.
 */
public class SimpleSpotFactory {
    public static Spot createSpot(SpotType spotType) {
        Spot spot = null;
        switch (spotType) {
            case BankSpot:
                spot = new BankSpot();
                break;
            case BonusCardSpot:
                spot = new BonusCardSpot();
                break;
            case BonusTicketSpot:
                spot = new BonusTicketSpot();
                break;
            case CardShopSpot:
                spot = new CardShopSpot();
                break;
            case EmptySpot:
                spot = new EmptySpot();
                break;
            case EstateSpot:
                double basePrice = (new Random().nextInt(1500) + 500);
                spot = new EstateSpot(basePrice);
                break;
            case LotterySpot:
                spot = new LotterySpot();
                break;
            case NewsSpot:
                spot = new NewsSpot();
                break;

        }
        return spot;
    }
}
