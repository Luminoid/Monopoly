package action.event;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.Player;
import model.card.Card;
import model.card.CardType;
import model.card.SimpleCardFactory;

/**
 * Created by Ethan on 16/4/30.
 */
public class BonusCardEvent extends Event {
    @Override
    public void toggle(Player player) {
        CardType cardType = Card.generateCard();
        player.addCard(cardType);
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr(player.getName() + " 获得一张" + SimpleCardFactory.createCard(cardType).getName());
    }
}
