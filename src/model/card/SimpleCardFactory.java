package model.card;

/**
 * Created by Ethan on 16/4/27.
 */
public class SimpleCardFactory {
    public static Card createCard(CardType cardType) {
        Card card = null;
        switch (cardType) {
            case BARRICADE_CARD:
                card = new BarricadeCard();
                break;
            case CASH_EQUALITY_CARD:
                card = new CashEqualityCard();
                break;
            case CONTROLLED_DICE_CARD:
                card = new ControlledDiceCard();
                break;
            case LOTTERY_CARD:
                card = new LotteryCard();
                break;
            case PURCHASE_LAND_CARD:
                card = new PurchaseLandCard();
                break;
            case REVERSE_CARD:
                card = new ReverseCard();
                break;
            case STAY_CARD:
                card = new StayCard();
                break;
        }
        return card;
    }
}
