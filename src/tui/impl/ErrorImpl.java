package tui.impl;

import action.command.ErrorCommand;

/**
 * Created by Ethan on 16/4/30.
 */
public class ErrorImpl extends ErrorCommand {
    @Override
    public void action() {
        System.out.println(getCommandStr());
    }
}
