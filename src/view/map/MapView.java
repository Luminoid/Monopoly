package view.map;

import model.Kernal;
import model.Player;
import model.spot.EstateSpot;
import model.spot.Spot;

/**
 * Created by Ethan on 16/4/29.
 */
public class MapView {
    private static final String BLANK = "　";
    private static final String BANK_SPOT = "银";
    private static final String BONUS_CARD_SPOT = "卡";
    private static final String BONUS_TICKET_SPOT = "券";
    private static final String CARD_SHOP_SPOT = "道";
    private static final String EMPTY_SPOT = "空";
    private static final String LOTTERY_SPOT = "彩";
    private static final String NEWS_SPOT = "新";
    private static final String ESTATE_SPOT = "地①②③④";
    private static final String PALYER_MARK = "ＡＢＣＤ";

    public static Spot getPlayerSpot(Player player) {
        TuiMap map = (TuiMap) Kernal.getInstance().getMap();
        return map.getSpots().get(player.getPosition());
    }

    public static Spot getDistantSpot(Player player, int distance) {
        TuiMap map = (TuiMap) Kernal.getInstance().getMap();
        return map.getSpots().get(player.getPosition());
    }

    public static SpotLoc getPlayerLoc(Player player) {
        TuiMap map = (TuiMap) Kernal.getInstance().getMap();
        return map.spotLoc.get(map.getSpots().get(player.getPosition()));
    }

    public static String getEstateOwner(EstateSpot spot) {
        String ret = "";
        if (spot.getOwner() == null) {
            ret += ESTATE_SPOT.charAt(0);
        } else {
            ret += ESTATE_SPOT.charAt(spot.getOwner().getId());
        }
        return ret;
    }

    public static String getSpotTag(Spot spot) {
        switch (spot.getSpotType()) {
            case BankSpot:
                return BANK_SPOT;
            case BonusCardSpot:
                return BONUS_CARD_SPOT;
            case BonusTicketSpot:
                return BONUS_TICKET_SPOT;
            case CardShopSpot:
                return CARD_SHOP_SPOT;
            case EmptySpot:
                return EMPTY_SPOT;
            case LotterySpot:
                return LOTTERY_SPOT;
            case NewsSpot:
                return NEWS_SPOT;
            case EstateSpot:
                return getEstateOwner((EstateSpot) spot);
            default:
                return BLANK;
        }
    }

    private static String[][] constructMap() {
        TuiMap map = (TuiMap) Kernal.getInstance().getMap();
        String[][] mapShape = new String[map.getHeight()][map.getWidth()];
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++)
                mapShape[i][j] = BLANK;
        map.getSpots().stream().forEach(e -> mapShape[map.spotLoc.get(e).getY()][map.spotLoc.get(e).getX()] = getSpotTag(e));
        return mapShape;
    }

    public static void printMap() {
        String[][] mapShape = constructMap();
        for (String[] s1 : mapShape) {
            for (String s2 : s1) {
                System.out.print(s2);
            }
            System.out.println();
        }
    }

    public static void printMapwithPlayer() {
        TuiMap map = (TuiMap) Kernal.getInstance().getMap();
        String[][] mapShape = constructMap();
        Kernal.getInstance().getPlayers().stream().forEach(e ->
                mapShape[getPlayerLoc(e).getY()][getPlayerLoc(e).getX()]
                        = PALYER_MARK.charAt(e.getId() - 1) + "");

        for (String[] s1 : mapShape) {
            for (String s2 : s1) {
                System.out.print(s2);
            }
            System.out.println();
        }
    }
}
