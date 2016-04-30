package model.card;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommamdFactory;
import model.Kernal;
import model.Player;

import java.util.stream.Stream;

/**
 * Created by Ethan on 16/4/27.
 */
public class CashEqualityCard extends Card {
    public CashEqualityCard() {
        this.name = "均富卡";
        this.description = "将所有人的现金平均分配";
        this.value = 8;
    }

    @Override
    public boolean use(Player player) {
        double averageCash = Kernal.getInstance().getPlayers().stream().filter(e -> !e.isBankrupt()).
                mapToDouble(Player::getCash).average().getAsDouble();
        Kernal.getInstance().getPlayers().stream().filter(e -> !e.isBankrupt()).forEach(e -> e.setCash(averageCash));
        PromptCommand command = (PromptCommand) SimpleCommamdFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr(player.getName() + " 使用了均富卡");
        return true;
    }
}
