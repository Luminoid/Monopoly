package model.card;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.Player;

/**
 * Created by Ethan on 16/4/27.
 */
public class ReverseCard extends Card {
    public ReverseCard() {
        this.name = "转向卡";
        this.description = "使目标掉转方向";
        this.value = 2;
        this.cardType = CardType.REVERSE_CARD;
    }

    @Override
    public boolean use(Player player) {
        player.changeOrientation();
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr("您使用了转向卡");
        return true;
    }
}
