package model.card;

/**
 * Created by Ethan on 16/4/27.
 */
public class SimpleCardFactory {
    public Card createCard(CardType type) {
        Card card = null;
        switch (type) {
            case BarricadeCard:
                card = new BarricadeCard();
                break;
            case CashEqualityCard:
                card = new CashEqualityCard();
                break;
            case ControlledDiceCard:
                card = new ControlledDiceCard();
                break;
            case LotteryCard:
                card = new LotteryCard();
                break;
            case PurchaseLandCard:
                card = new PurchaseLandCard();
                break;
            case ReverseCard:
                card = new ReverseCard();
                break;
            case StayCard:
                card = new StayCard();
                break;
        }
        return card;
    }
}
