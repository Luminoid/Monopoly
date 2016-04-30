package model.card;

import model.Player;

/**
 * Created by Ethan on 16/4/27.
 */
public class ReverseCard extends Card {
    public ReverseCard() {
        this.name = "转向卡";
        this.description = "使目标掉转方向";
        this.value = 2;
    }

    @Override
    public boolean use(Player player) {
        return false;
    }
}
