package util;

import model.Player;

/**
 * Created by Ethan on 16/5/1.
 */
public class playerTool {
    public static double getAsset(Player player) {
        return player.getCash() + player.getDeposit() +
                player.getHouses().stream().map(house -> house.getBasePrice() * house.getLevel()).reduce(0.0, (a, b) -> a + b);
    }
}
