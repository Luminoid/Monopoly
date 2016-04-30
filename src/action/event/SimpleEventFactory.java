package action.event;

/**
 * Created by Ethan on 16/4/30.
 */
public class SimpleEventFactory {
    public static Event createEvent(EventType eventType) {
        Event event = null;
        switch (eventType) {
            case BANK_EVENT:
                event = new BankEvent();
                break;
            case BONUS_CARD_EVENT:
                event = new BonusCardEvent();
                break;
            case BONUS_TICKET_EVENT:
                event = new BonusTicketEvent();
                break;
            case CARD_SHOP_EVENT:
                event = new CardShopEvent();
                break;
            case ESTETE_EVENT:
                event = new EstateEvent();
                break;
            case LOTTERY_EVENT:
                event = new LotteryEvent();
                break;
            case NEWS_EVENT:
                event = new NewsEvent();
                break;
        }
        return event;
    }
}
