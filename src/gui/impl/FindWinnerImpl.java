package gui.impl;

import action.command.FindWinnerCommand;

/**
 * Created by Ethan on 16/4/30.
 */
public class FindWinnerImpl extends FindWinnerCommand {
    @Override
    public void action() {
        System.out.println(getCommandStr() + " 获胜！");
        System.out.println("游戏结束");
    }
}
