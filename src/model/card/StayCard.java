package model.card;

import model.Player;

/**
 * Created by Ethan on 16/4/27.
 */
public class StayCard extends Card {
    public StayCard() {
        this.name = "滞留卡";
        this.description = "该回合停留在原地,并再次触发原地事件";
        this.value = 3;
    }

    @Override
    public boolean use(Player player) {
        return false;
    }
}
