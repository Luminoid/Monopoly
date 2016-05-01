package model.card;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.Dice;
import model.Player;

/**
 * Created by Ethan on 16/4/27.
 */
public class StayCard extends Card {
    public StayCard() {
        this.name = "滞留卡";
        this.description = "该回合停留在原地,并再次触发原地事件";
        this.value = 3;
        this.cardType = CardType.STAY_CARD;
    }

    @Override
    public boolean use(Player player) {
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr("您使用了滞留卡");
        Dice.getInstance().setValue(0);
        return true;
    }
}
