package view.impl;

import action.command.PromptCommand;

/**
 * Created by Ethan on 16/4/30.
 */
public class PromptImpl extends PromptCommand {
    @Override
    public void action() {
        System.out.println(getCommandStr());
    }
}
