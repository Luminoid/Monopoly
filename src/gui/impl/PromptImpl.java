package gui.impl;

import action.command.PromptCommand;
import gui.view.RootLayoutController;

/**
 * Created by Ethan on 16/4/30.
 */
public class PromptImpl extends PromptCommand {
    private static RootLayoutController controller;

    @Override
    public void action() {
        controller.promptText(getCommandStr());
    }

    public void setViewController(RootLayoutController controller) {
        this.controller = controller;
    }
}
