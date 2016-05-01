package action.event;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.Player;

/**
 * Created by Ethan on 16/4/30.
 */
public class BonusTicketEvent extends Event {
    @Override
    public void toggle(Player player) {
        int ticketNum = (int) (Math.random() * 10 + 5);
        player.setTicket(player.getTicket() + ticketNum);
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr(player.getName() + "获得了" + ticketNum + "点券");
    }
}
