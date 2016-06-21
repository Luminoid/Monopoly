package util;

import model.Kernel;
import model.Player;
import model.map.RegMap;
import model.map.SpotLoc;
import model.spot.Spot;

/**
 * Created by Ethan on 16/5/1.
 */
public class PlayerUtil {
    public static Spot getPlayerSpot(Player player) {
        RegMap map = (RegMap) Kernel.getInstance().getMap();
        return map.getSpotList().get(player.getPosition());
    }

    public static Spot getDistantSpot(Player player, int distance) {
        RegMap map = (RegMap) Kernel.getInstance().getMap();
        distance = FormatTool.distanceWithOrientation(player, distance);
        return map.getSpotList().get(player.checkPosition(distance));
    }

    public static SpotLoc getPlayerLoc(Player player) {
        RegMap map = (RegMap) Kernel.getInstance().getMap();
        return map.spotLoc.get(map.getSpotList().get(player.checkPosition(0)));
    }

    public static double getAsset(Player player) {
        return player.getCash() + player.getDeposit() +
                player.getHouses().stream().map(house -> house.getBasePrice() * house.getLevel()).reduce(0.0, (a, b) -> a + b);
    }
}
