package tui.map;

import model.Kernel;
import model.map.MapConstructor;
import util.PlayerUtil;

/**
 * Created by Ethan on 16/4/29.
 */
public class MapView {
    private static final String PLAYER_MARK = "ＡＢＣＤ";

    public static void printMap() {
        String[][] mapShape = MapConstructor.constructMap();
        for (String[] s1 : mapShape) {
            for (String s2 : s1) {
                System.out.print(s2);
            }
            System.out.println();
        }
    }

    public static void printMapWithPlayer() {
        String[][] mapShape = MapConstructor.constructMap();
        Kernel.getInstance().getPlayers().stream().forEach(e ->
                mapShape[PlayerUtil.getPlayerLoc(e).getY()][PlayerUtil.getPlayerLoc(e).getX()]
                        = PLAYER_MARK.charAt(e.getId() - 1) + "");

        for (String[] s1 : mapShape) {
            for (String s2 : s1) {
                System.out.print(s2);
            }
            System.out.println();
        }
    }
}
