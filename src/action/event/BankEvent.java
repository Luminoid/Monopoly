package action.event;

import gui.menu.BankMenu;
import model.Player;

/**
 * Created by Ethan on 16/4/30.
 */
public class BankEvent extends Event {
    @Override
    public void toggle(Player player) {
        BankMenu.displayBankMenu(player);
    }
}
