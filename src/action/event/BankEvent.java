package action.event;

import model.Player;
import tui.menu.BankMenu;

/**
 * Created by Ethan on 16/4/30.
 */
public class BankEvent extends Event {
    @Override
    public void toggle(Player player) {
        BankMenu.displayBankMenu(player);
    }
}
