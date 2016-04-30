package action.event;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommamdFactory;
import model.Player;
import model.card.Card;
import model.card.CardType;
import model.card.SimpleCardFactory;

import java.util.ArrayList;

/**
 * Created by Ethan on 16/4/30.
 */
public class BonusCardEvent extends Event {
    @Override
    public void toggle(Player player) {
        Card card = SimpleCardFactory.createCard(generateCard());
        player.addCard(card);
        PromptCommand command = (PromptCommand) SimpleCommamdFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr(player.getName() + " 获得一张" + card.getName());
    }

    private static CardType generateCard() {
        ArrayList<CardType> cards = new ArrayList<>();
        int totalWeight = 0;
        for (CardType type : CardType.values()) {
            int num = SimpleCardFactory.createCard(type).getValue(); // weight
            totalWeight += num;
            for (int i = 0; i < num; i++) {
                cards.add(type);
            }
        }
        int index = (int) (Math.random() * totalWeight);
        return cards.get(index);
    }
}
