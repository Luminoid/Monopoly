package action.event;

import model.Player;
import tui.menu.CardShopMenu;

/**
 * Created by Ethan on 16/4/30.
 */
public class CardShopEvent extends Event {
    @Override
    public void toggle(Player player) {
        CardShopMenu.displayCardShopMenu(player);
    }
}
