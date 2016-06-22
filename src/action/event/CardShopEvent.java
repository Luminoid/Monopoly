package action.event;

import gui.menu.CardShopMenu;
import model.Player;

/**
 * Created by Ethan on 16/4/30.
 */
public class CardShopEvent extends Event {
    @Override
    public void toggle(Player player) {
        CardShopMenu.displayCardShopMenu(player);
    }
}
