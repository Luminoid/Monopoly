package model.card;

import model.Player;

/**
 * Created by Ethan on 16/4/27.
 */
public class PurchaseLandCard extends Card {
    public PurchaseLandCard() {
        this.name = "购地卡";
        this.description = "强行用现价购买自己当前所在位置的土地,当然发动者不能购买自己的房屋";
        this.value = 15;
    }

    @Override
    public boolean use(Player player) {
        return false;
    }
}
