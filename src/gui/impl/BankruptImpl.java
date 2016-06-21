package gui.impl;

import action.command.BankruptCommand;

/**
 * Created by Ethan on 16/4/30.
 */
public class BankruptImpl extends BankruptCommand {
    @Override
    public void action() {
        getPlayer().setBankrupt(true);
        System.out.println(getPlayer().getName() + " 已破产！");
    }
}
