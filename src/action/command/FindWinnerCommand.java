package action.command;

/**
 * Created by Ethan on 16/4/30.
 */
public abstract class FindWinnerCommand implements Command {
    private String commandStr;

    public String getCommandStr() {
        return commandStr;
    }

    public void setCommandStr(String commandStr) {
        this.commandStr = commandStr;
        action();
    }
}
