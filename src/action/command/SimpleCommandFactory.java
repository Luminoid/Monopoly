package action.command;

import gui.impl.*;

/**
 * Created by Ethan on 16/4/30.
 */
public class SimpleCommandFactory {
    public static Command createCommand(CommandType commandType) {
        Command command = null;
        switch (commandType) {
            case FIND_WINNER_COMMAND:
                command = new FindWinnerImpl();
                break;
            case ERROR_COMMAND:
                command = new ErrorImpl();
                break;
            case PROMPT_COMMAND:
                command = new PromptImpl();
                break;
            case SELL_ESTATE_COMMAND:
                command = new SellEstateImpl();
                break;
            case BANKRUPT_COMMAND:
                command = new BankruptImpl();
                break;
            case YES_OR_NO_REQUEST:
                command = new YesOrNoImpl();
                break;
            case INT_RANGE_REQUEST:
                command = new IntRangeImpl();
                break;
        }
        return command;
    }
}
