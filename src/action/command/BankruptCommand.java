package action.command;

import model.Player;

/**
 * Created by Ethan on 16/4/30.
 */
public abstract class BankruptCommand implements Command {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
