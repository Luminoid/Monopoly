package gui.impl;

import action.command.SellEstateCommand;

/**
 * Created by Ethan on 16/4/30.
 */
public class SellEstateImpl extends SellEstateCommand {
    @Override
    public void action() {
        System.out.println(getCommandStr());
    }
}
