package util;

import model.Player;

/**
 * Created by Ethan on 16/5/1.
 */
public class FormatTool {
    public static String formatMoney(double money) {
        return String.format("%.1f", money);
    }

    public static String formatRate(double value) {
        value *= 100;
        return String.format("%.1f", value)+"%";
    }

    public static int distanceWithOrientation(Player player, int distance) {
        distance *= (player.getOrientation() == PlayerOrientation.FORWARD) ? 1 : -1;
        return distance;
    }
}
